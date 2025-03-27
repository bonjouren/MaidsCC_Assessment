```pseudo
START
  Greet the user: "Are you a returning user?" 
  IF isReturningUser THEN
    Initialize attributes:
      - isReturningUser = true
      - userName = "Returning User"
      - userAge = 30
      - isVegetarian = false
      - isReligious = false
      - livesAlone = false
      - newsletterSubscription = false
      - userLanguage = "english"
      - interests = "Product A"
      - shippingAddress = ""
      - shippingSpeed = "standard"
      - serviceDate = ""
      - promoCode = ""
      - promoCodeValid = false
      - premiumSupportEnabled = false
  ELSE
    Collect basic info (userName, userAge)
    IF age < 18 THEN
      Ask for guardian consent
      IF no guardian consent THEN Deactivate profile, EXIT
    END IF
  END IF

  Ask lifestyle questions (livesAlone, isVegetarian, isReligious)

  Ask for newsletter subscription
  Ask for language preference (default to English)

  Collect user interest (Product A, Product B, or Service X)
  IF Product A or B THEN
    Collect shipping address and speed
  ELSE IF Service X THEN
    Collect service date (validate date format)
  END IF

  IF returningUser THEN
    Ask for premium support (if used before, reflect this in flow)
  END IF

  Ask for promo code
  IF invalid promo code THEN
    Prompt user to retry or skip

  Summarize all collected details
  Ask user to confirm or reject details
  IF confirmed THEN
    Finalize profile
  ELSE
    EXIT
  END IF
END
```
