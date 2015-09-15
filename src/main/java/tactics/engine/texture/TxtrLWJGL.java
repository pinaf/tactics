package tactics.engine.texture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.validation.constraints.NotNull;
import org.lwjgl.opengl.GL11;

/**
 * A LWJGL backed implementation of {@link Texture}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class TxtrLWJGL implements Texture {

    /**
     * OpenGL target.
     */
    private static final int TARGET = GL11.GL_TEXTURE_2D;

    /**
     * Texture ID inside OpenGL.
     */
    private transient int idt;

    /**
     * Width.
     */
    private transient int width;

    /**
     * Height.
     */
    private transient int height;

    /**
     * Ctor.
     * @param img Location of image.
     */
    public TxtrLWJGL(@NotNull final String img) throws IOException {
        this(
            new BufferedInputStream(
                TxtrLWJGL.class.getClassLoader().getResourceAsStream(img)
            )
        );
    }

    /**
     * Ctor.
     * @param contents Input stream with image contents.
     */
    public TxtrLWJGL(@NotNull final InputStream contents) throws IOException {
        GL11.glEnable(TxtrLWJGL.TARGET);
        final BufferedImage image = ImageIO.read(contents);
        if (image == null) {
            throw new IllegalArgumentException("Invalid image for texture.");
        }
        this.create(image);
    }

    @Override
    public int width() {
        return this.width;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public void load() {
        this.bind();
    }

    @Override
    public void draw(final float xcoord, final float ycoord) {
        GL11.glEnable(TxtrLWJGL.TARGET);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex2f(xcoord, ycoord);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex2f(xcoord, ycoord + (float) this.height);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex2f(xcoord + (float) this.width, ycoord + (float) this.height);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex2f(xcoord + (float) this.width, ycoord);
        GL11.glEnd();
    }

    /**
     * Binds this texture to OpenGL.
     */
    private void bind() {
        GL11.glBindTexture(TxtrLWJGL.TARGET, this.idt);
    }

    /**
     * Creates the OpenGL texture.
     * @param image Input image.
     */
    private void create(final BufferedImage image) {
        this.idt = GL11.glGenTextures();
        this.bind();
        final int format;
        if (image.getColorModel().hasAlpha()) {
            format = GL11.GL_RGBA;
        } else {
            format = GL11.GL_RGB;
        }
        final ByteBuffer bytes = this.convertImage(image);
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
        GL11.glTexParameteri(
            TxtrLWJGL.TARGET, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR
        );
        GL11.glTexParameteri(
            TxtrLWJGL.TARGET, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR
        );
        GL11.glTexImage2D(
            TxtrLWJGL.TARGET,
            0,
            GL11.GL_RGBA,
            this.width,
            this.height,
            0,
            format,
            GL11.GL_UNSIGNED_BYTE,
            bytes
        );
        GL11.glDisable(TxtrLWJGL.TARGET);
    }

    /**
     * Converts the buffered image to an OpenGL texture.
     * @param image The image to convert.
     * @return A byte buffer containing the OpenGL texture data.
     */
    @SuppressWarnings({"UseOfObsoleteCollectionType", "CollectionWithoutInitialCapacity"})
    private ByteBuffer convertImage(final BufferedImage image) {
        this.width = 2;
        this.height = 2;
        while (this.width < image.getWidth()) {
            this.width <<= 1;
        }
        while (this.height < image.getHeight()) {
            this.height <<= 1;
        }
        final ComponentColorModel alpha = new ComponentColorModel(
            ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[]{8, 8, 8, 8},
            true,
            false,
            Transparency.TRANSLUCENT,
            DataBuffer.TYPE_BYTE
        );
        final ComponentColorModel opaque = new ComponentColorModel(
            ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[]{8, 8, 8, 0},
            false,
            false,
            Transparency.OPAQUE,
            DataBuffer.TYPE_BYTE
        );
        // create a raster that can be used by OpenGL as a source for a texture
        final WritableRaster raster;
        final BufferedImage converted;
        if (image.getColorModel().hasAlpha()) {
            raster = Raster.createInterleavedRaster(
                DataBuffer.TYPE_BYTE, this.width, this.height, 4, null
            );
            converted = new BufferedImage(
                alpha, raster, false, new Hashtable()
            );
        } else {
            raster = Raster.createInterleavedRaster(
                DataBuffer.TYPE_BYTE, this.width, this.height, 3, null
            );
            converted = new BufferedImage(
                opaque, raster, false, new Hashtable()
            );
        }

        // copy the source image into the produced image
        final Graphics graphics = converted.getGraphics();
        graphics.setColor(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        graphics.fillRect(0, 0, this.width, this.height);
        graphics.drawImage(image, 0, 0, null);

        final byte[] data =
            ((DataBufferByte) converted.getRaster().getDataBuffer()).getData();
        final ByteBuffer buffer = ByteBuffer.allocateDirect(data.length);
        buffer.order(ByteOrder.nativeOrder());
        buffer.put(data, 0, data.length);
        buffer.flip();
        return buffer;
    }

}
