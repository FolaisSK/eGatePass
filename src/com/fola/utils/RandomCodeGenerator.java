package com.fola.utils;

import java.security.SecureRandom;

public class RandomCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHJKMNPQRSTUVWXYZ123456789";
    //private static final int LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    public static String generateCode(int numberOfCharacters) {
        String id = "";

        for (int count = 0; count < numberOfCharacters; count++) {
            int index = random.nextInt(CHARACTERS.length());
            id += CHARACTERS.charAt(index);
        }

        return id;
    }
}
