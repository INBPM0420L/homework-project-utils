package util.javafx;

import javafx.scene.image.Image;

import java.util.Optional;

/**
 * Represents a key-based image storage facility that enables the retrieval of
 * images based on keys.
 *
 * @param <T> represents the type of the keys
 */
public interface ImageStorage<T> {

    /**
     * {@return an {@code Optional} describing the image associated with the key
     * specified, or an empty optional if no image is associated with the key}
     *
     * @param key a key for which the associated image should be returned
     */
    Optional<Image> get(T key);

}
