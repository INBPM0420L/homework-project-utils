package util.javafx;

import javafx.scene.image.Image;

import java.lang.System.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * An implementation of the {@code ImageStorage} interface that associates PNG
 * images with the constants of an enum. The images representing the constants
 * of the enum specified are loaded via the classloader when an instance of the
 * class is created.
 *
 * <p>Consider, for example, the following enum:
 * {@snippet :
 * package chess.puzzle;
 * enum Piece {
 *     WHITE_KNIGHT,
 *     BLACK_KNIGHT
 * }
 * }
 * The code {@code new EnumImageStorage(Piece.class)} will search for
 * {@code white_knight.png} and {@code black_knight.png} in the
 * {@code chess.puzzle} package, respectively. Thus, in the case of an Apache
 * Maven project, the images should be kept under the
 * {@code src/main/resources/chess/puzzle} directory. It is not needed to have
 * an image for each of the enum constants.
 *
 * @param <T> an enum whose constants are represented by PNG images
 */
public class EnumImageStorage<T extends Enum<?>> implements ImageStorage<T> {

    private static final Logger logger = System.getLogger(EnumImageStorage.class.getName());

    private final Map<T, Image> map = new HashMap<>();

    /**
     * Creates an {@code EnumImageStorage} instance for the enum specified.
     *
     * @param enumClass a {@code Class} object representing an enum
     */
    public EnumImageStorage(Class<T> enumClass) {
        var path = enumClass.getPackage().getName().replace(".", "/");
        for (var constant : enumClass.getEnumConstants()) {
            var url = String.format("%s/%s.png", path, constant.name().toLowerCase());
            try {
                map.put(constant, new Image(url));
                logger.log(Logger.Level.INFO, "Loaded image from {0}", url);
            } catch (Exception e) {
                // Failed to load image
                logger.log(Logger.Level.WARNING, "Failed to load image from {0}", url);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param constant the constant for which the associated image should be
     *                 returned
     */
    @Override
    public Optional<Image> get(T constant) {
        return Optional.ofNullable(map.get(constant));
    }

}
