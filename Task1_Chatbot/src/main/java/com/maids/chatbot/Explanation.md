#### **Attributes Chosen:**

1. **isReturningUser (Boolean)**: This attribute helps to identify if the user is returning. If true, we use the existing profile information; if false, we prompt the user for basic details (name, age, etc.).
    - **Why chosen**: It's essential for controlling the flow. If the user is returning, the chatbot skips profile creation and uses existing information.

2. **userName (String), userAge (Integer)**: These are fundamental attributes to identify and categorize the user.
    - **Why chosen**: Collecting user name and age is necessary for personalization and for handling age-specific flows (guardian consent for users under 18).

3. **guardianConsent (Boolean)**: This attribute ensures that users under 18 have the required guardian consent to proceed.
    - **Why chosen**: Legal requirements often necessitate guardian consent for users under 18.

4. **isVegetarian, isReligious, livesAlone (Boolean)**: These lifestyle attributes provide a more personalized experience.
    - **Why chosen**: These attributes allow the chatbot to offer more tailored services or product recommendations.

5. **newsletterSubscription (Boolean)**: To track whether the user subscribes to the newsletter.
    - **Why chosen**: Useful for user engagement and marketing purposes.

6. **userLanguage (String)**: Stores the user’s language preference (English, Spanish, French).
    - **Why chosen**: Necessary for ensuring the chatbot communicates with the user in their preferred language.

7. **interests (String)**: Tracks the user's interest in products or services.
    - **Why chosen**: Helps in determining what to offer the user—whether it’s a product, service, or other options.

8. **shippingAddress (String), shippingSpeed (String)**: Required if the user shows interest in a physical product.
    - **Why chosen**: These attributes are vital for processing orders.

9. **serviceDate (String)**: Required if the user selects Service X, so the chatbot can schedule the service.
    - **Why chosen**: Collecting the service date ensures that the user gets an appropriate time slot.

10. **promoCode (String), promoCodeValid (Boolean)**: These attributes track the promo code entered by the user and whether it is valid.
    - **Why chosen**: Needed to apply promotional discounts and validate the format of the promo code.

#### **Attributes Excluded:**

1. **debugMode (Boolean)**: Not required for the flow of the chatbot. It is typically used for development or troubleshooting.
    - **Why excluded**: The chatbot's goal is to interact with the user, not to debug.

2. **questionCounter (Integer)**: Not essential for the user interaction flow. This attribute would only count the number of questions asked, which is unnecessary.
    - **Why excluded**: We don’t need to track how many questions are asked—focus is on responses.

3. **discountAmount (Integer)**: Not needed because we are using a promo code string to apply discounts, and the amount can be calculated directly from the code.
    - **Why excluded**: The promo code handles the discount logic.

4. **profileStatus (String)**: Redundant as the `active` boolean already manages the status of the profile (whether the user is active or not).
    - **Why excluded**: The `active` attribute is sufficient.

5. **configurationMode (Integer)**: Irrelevant to the user's interaction. Not needed in the flow.
    - **Why excluded**: Configuration mode is not used in the user interaction logic.

6. **lifestyleFlags (4bit binary)**: Overcomplicates the process. We use individual booleans to represent the lifestyle choices (vegetarian, religious, lives alone).
    - **Why excluded**: Binary encoding would be unnecessarily complex for this use case.

7. **mysteryFlag (String)**: This attribute has no relevance to the chatbot’s functionality.
    - **Why excluded**: No user interaction requires this flag.

8. **overridePolicy (Boolean)**: Not needed as we don't have any overriding logic in the current chatbot flow.
    - **Why excluded**: Unnecessary for the scope of the task.

---
