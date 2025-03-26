package com.maids.chatbot;

import java.util.*;

public class Chatbot {

    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Object> userProfile = new HashMap<>();

    public static void main(String[] args) {
        startChatbot();
    }

    private static void startChatbot() {
        greetUser();
        collectBasicInfo();
        askLifestyleQuestions();
        handleNewsletterSubscription();
        askLanguagePreference();
        collectUserInterest();
        checkPremiumSupport();
        handlePromoCode();
        summarizeAndConfirm();
    }

    private static void greetUser() {
        System.out.println("Welcome to our service! Are you a returning user? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();
        userProfile.put("isReturningUser", response.equals("yes"));
    }

    private static void collectBasicInfo() {
        System.out.print("Enter your name: ");
        userProfile.put("userName", scanner.nextLine().trim());

        System.out.print("Enter your age: ");
        int age = getValidInteger();
        userProfile.put("userAge", age);

        if (age < 18) {
            System.out.println("Do you have guardian consent? (yes/no)");
            boolean guardianConsent = scanner.nextLine().trim().equalsIgnoreCase("yes");
            userProfile.put("guardianConsent", guardianConsent);
            if (!guardianConsent) {
                userProfile.put("active", false);
                System.out.println("Profile deactivated due to lack of consent.");
                System.exit(0);
            }
        }
    }

    private static void askLifestyleQuestions() {
        userProfile.put("livesAlone", getBooleanInput("Do you live alone? (yes/no)"));
        userProfile.put("isVegetarian", getBooleanInput("Are you vegetarian? (yes/no)"));
        userProfile.put("isReligious", getBooleanInput("Are you religious? (yes/no)"));
    }

    private static void handleNewsletterSubscription() {
        userProfile.put("newsletterSubscription", getBooleanInput("Would you like to subscribe to our newsletter? (yes/no)"));
    }

    private static void askLanguagePreference() {
        System.out.println("Preferred language (English, Spanish, French): ");
        String language = scanner.nextLine().trim().toLowerCase();
        if (!language.equals("english") && !language.equals("spanish") && !language.equals("french")) {
            language = "english";
        }
        userProfile.put("userLanguage", language);
    }

    private static void collectUserInterest() {
        System.out.println("Choose interest: Product A, Product B, Service X");
        String interest = scanner.nextLine().trim().toLowerCase();
        userProfile.put("interests", interest);

        if (interest.equals("product a") || interest.equals("product b")) {
            System.out.print("Enter shipping address: ");
            userProfile.put("shippingAddress", scanner.nextLine().trim());
            System.out.print("Enter shipping speed: ");
            userProfile.put("shippingSpeed", scanner.nextLine().trim());
        } else if (interest.equals("service x")) {
            System.out.print("Enter preferred service date (YYYY-MM-DD): ");
            userProfile.put("serviceDate", scanner.nextLine().trim());
        }
    }

    private static void checkPremiumSupport() {
        if ((boolean) userProfile.get("isReturningUser")) {
            System.out.println("Would you like to enable premium support? (yes/no)");
            userProfile.put("premiumSupportEnabled", getBooleanInput(""));
        }
    }

    private static void handlePromoCode() {
        System.out.println("Do you have a promo code? (yes/no)");
        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            System.out.print("Enter promo code: ");
            String promoCode = scanner.nextLine().trim();
            if (validatePromoCode(promoCode)) {
                userProfile.put("promoCode", promoCode);
                userProfile.put("promoCodeValid", true);
            } else {
                System.out.println("Invalid promo code format.");
            }
        }
    }

    private static void summarizeAndConfirm() {
        System.out.println("Summary of your details:");
        userProfile.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("Confirm your details? (yes/no)");
        if (!scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            System.out.println("Profile not confirmed. Exiting.");
            System.exit(0);
        }

        System.out.println("Thank you! Your profile is complete.");
    }

    private static boolean getBooleanInput(String prompt) {
        System.out.println(prompt);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

    private static int getValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter again: ");
            }
        }
    }

    private static boolean validatePromoCode(String promoCode) {
        return promoCode.matches("[A-Z]{3}[0-9]{4}[@#$%^&*]{2}[a-z]{3}");
    }
}
