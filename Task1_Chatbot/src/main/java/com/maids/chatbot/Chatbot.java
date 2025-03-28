package com.maids.chatbot;

import java.util.*;
import java.text.*;

public class Chatbot {

    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Object> userProfile = new HashMap<>();
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
    private static final String PROMO_CODE_REGEX = "[A-Z]{3}[0-9]{4}[@#$%^&*]{2}[a-z]{3}";

    public static void main(String[] args) {
        startChatbot();
    }

    private static void startChatbot() {
        greetUser();

        // Initialize attributes only if the user is a returning user
        if ((boolean) userProfile.get("isReturningUser")) {
            initializeReturningUserProfile();
        } else {
            collectBasicInfo();  // New users will go through the full profile creation process
        }
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
        if ((boolean) userProfile.get("isReturningUser")) {
            System.out.println("Welcome back!");
        } else {
            System.out.println("Hi there! Let's create your profile.");
        }
    }
    // Method to initialize the profile of returning users
    private static void initializeReturningUserProfile() {
        // Default values for returning users
        userProfile.putIfAbsent("active", true);
        userProfile.putIfAbsent("confirmation", false);
        userProfile.putIfAbsent("guardianConsent", true);  // assuming true for returning users, adjust as needed
        userProfile.putIfAbsent("isVegetarian", false);
        userProfile.putIfAbsent("isReligious", false);
        userProfile.putIfAbsent("premiumSupportEnabled", false);
        userProfile.putIfAbsent("newsletterSubscription", false);
        userProfile.putIfAbsent("userLanguage", "english");
        userProfile.putIfAbsent("interests", "Product A"); // Default interest
        userProfile.putIfAbsent("shippingAddress", "");  // Default empty address
        userProfile.putIfAbsent("shippingSpeed", "standard");  // Default shipping speed
        userProfile.putIfAbsent("serviceDate", ""); // Default empty service date
        userProfile.putIfAbsent("promoCode", ""); // Default empty promo code
        userProfile.putIfAbsent("promoCodeValid", false); // Default promo code validity to false
        userProfile.putIfAbsent("userName", "Returning User"); // Default name
        userProfile.putIfAbsent("userAge", 30); // Default age
        userProfile.putIfAbsent("livesAlone", false);  // Default to false
        userProfile.putIfAbsent("shippingAddress", "Unknown");  // Default empty address
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
            // Validate the service date format and re-prompt if invalid
            boolean validDate = false;
            while (!validDate) {
                System.out.print("Enter preferred service date (YYYY-MM-DD): ");
                String serviceDate = scanner.nextLine().trim();
                if (validateStrictDate(serviceDate)) {
                    userProfile.put("serviceDate", serviceDate);
                    validDate = true; // Exit loop if date is valid
                } else {
                    System.out.println("Invalid date format. Please enter in strict YYYY-MM-DD format.");
                }
            }
        }
    }

    private static boolean validateStrictDate(String date) {
        // Regular expression for validating YYYY-MM-DD format
        String regex = "^(\\d{4})-(\\d{2})-(\\d{2})$";
        if (date.matches(regex)) {
            // Check if the date is valid by parsing
            try {
                dateFormat.setLenient(false); // Strict date parsing
                dateFormat.parse(date);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
        return false;
    }



    private static void checkPremiumSupport() {
        if ((boolean) userProfile.get("isReturningUser")) {
            System.out.println("Would you like to enable premium support? (yes/no)");
            userProfile.put("premiumSupportEnabled", getBooleanInput(" "));
        }
    }

    private static void handlePromoCode() {
        System.out.println("Do you have a promo code? (yes/no)");
        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            while (true) {
                System.out.print("Enter promo code: ");
                String promoCode = scanner.nextLine().trim();
                if (validatePromoCode(promoCode)) {
                    userProfile.put("promoCode", promoCode);
                    userProfile.put("promoCodeValid", true);
                    break; // Exit the loop once a valid promo code is entered
                } else {
                    System.out.println("Invalid promo code format. Please try again or type 'skip' to skip.");
                    String retry = scanner.nextLine().trim().toLowerCase();
                    if (retry.equals("skip")) {
                        System.out.println("Skipping promo code entry.");
                        break; // Exit the loop if user skips
                    }
                }
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
        return promoCode.matches(PROMO_CODE_REGEX);
    }

    private static boolean validateDate(String date) {
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
