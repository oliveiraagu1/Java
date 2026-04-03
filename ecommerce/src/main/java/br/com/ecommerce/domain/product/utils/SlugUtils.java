package br.com.ecommerce.domain.product.utils;

import lombok.NoArgsConstructor;

import java.text.Normalizer;

@NoArgsConstructor
public class SlugUtils {

    public static String toSlug(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }

        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "")
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
    }
}
