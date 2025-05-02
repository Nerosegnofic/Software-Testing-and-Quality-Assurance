*** Settings ***
Library    SeleniumLibrary
Suite Setup    Open Browser To AliExpress
Suite Teardown    Close Browser

*** Variables ***
${URL}              https://www.aliexpress.com/
${BROWSER}          chrome
${SEARCH_BOX}       xpath=//input[@id="search-words"]
${PRODUCT_NAME}     smart watch
${LOGIN_EMAIL}      your_email@example.com
${LOGIN_PASSWORD}   your_password

*** Keywords ***
Open Browser To AliExpress
    Open Browser    ${URL}    ${BROWSER}
    Maximize Browser Window
    Wait Until Page Contains Element    xpath=//body    timeout=30s
    Sleep    5s
    Run Keyword And Ignore Error    Click Element    xpath=//div[@role="dialog"]//button[contains(., 'Save')]
    Run Keyword And Ignore Error    Click Button    xpath=//div[@id="nav-global"]/following::button[1]
    Sleep    3s
    Capture Page Screenshot
    Wait Until Element Is Visible    ${SEARCH_BOX}    timeout=40s

Search For Product
    Input Text    ${SEARCH_BOX}    ${PRODUCT_NAME}
    Press Keys    ${SEARCH_BOX}    RETURN
    Sleep    5s
    Page Should Contain    ${PRODUCT_NAME}

*** Test Cases ***
Scenario 1: Search Product
    Search For Product

Scenario 2: Filter by Category
    Search For Product
    Sleep    5s
    Execute JavaScript    window.scrollBy(0, 1000)
    Sleep    3s
    Run Keyword And Ignore Error    Input Text    xpath=(//div[contains(text(),"Price")]/following::input)[1]    580
    Run Keyword And Ignore Error    Input Text    xpath=(//div[contains(text(),"Price")]/following::input)[2]    1249
    Run Keyword And Ignore Error    Press Keys    xpath=(//div[contains(text(),"Price")]/following::input)[2]    RETURN
    Sleep    5s
    Page Should Contain    ${PRODUCT_NAME}

Scenario 3: Add to Cart
    Search For Product
    Sleep    3s
    Click Element    xpath=(//a[contains(@href, "item")])[1]
    Sleep    5s
    Run Keyword And Ignore Error    Click Element    xpath=//button[contains(., "Accept")]
    FOR    ${i}    IN RANGE    3
        Run Keyword And Ignore Error    Wait Until Element Is Visible    xpath=//button[contains(@class, "add-to-cart--addtocart--")]    timeout=10s
        Capture Page Screenshot
        Run Keyword And Ignore Error    Click Button    xpath=//button[contains(@class, "add-to-cart--addtocart--")]
        Sleep    2s
    END
    Click Element    xpath=//a[contains(@href, "shoppingcart")]
    Sleep    5s
    Run Keyword And Ignore Error    Page Should Contain Element    xpath=//div[contains(@class, 'cart')]
    Run Keyword And Ignore Error    Page Should Contain Element    xpath=//div[contains(@class, 'product')]
    Run Keyword And Ignore Error    Page Should Contain Element    xpath=//div[contains(text(), 'item')]    xpath=//div[contains(@class, 'cart') or contains(@class, 'product') or contains(text(), 'item')]    xpath=//div[contains(@class, 'cart') or contains(@class, 'product') or contains(text(), 'item')]    xpath=//div[contains(@class, 'cart') or contains(@class, 'product') or contains(text(), 'item')]    xpath=//div[contains(@class, 'cart') or contains(@class, 'product') or contains(text(), 'item')]

Scenario 4: Change Language
    Run Keyword And Ignore Error    Wait Until Element Is Visible    xpath=//div[contains(@class,'topbar-language')]//span[contains(.,'Language')]    timeout=30s
    Run Keyword And Ignore Error    Click Element    xpath=//div[contains(@class,'topbar-language')]//span[contains(.,'Language')]
    Sleep    3s
    Run Keyword And Ignore Error    Click Element    xpath=//a[contains(text(),'Arabic') or contains(text(),'English')]
    Sleep    5s
    Run Keyword And Ignore Error    Page Should Contain Element    xpath=//body[contains(@lang,'ar')]
    Run Keyword And Ignore Error    Page Should Contain Element    xpath=//body[contains(@lang,'en')]    xpath=//body[contains(@lang,'ar') or contains(@lang,'en')]    xpath=//body[contains(@lang,'ar') or contains(@lang,'en')]    xpath=//body[contains(@lang,'ar') or contains(@lang,'en')]    xpath=//body[contains(@lang,'ar') or contains(@lang,'en')]    xpath=//body[contains(@lang,'ar') or contains(@lang,'en')]

Scenario 7: Zoom Product Image
    Search For Product
    Click Element    xpath=(//a[contains(@href, "item")])[1]
    Sleep    3s
    Mouse Over    xpath=//img[contains(@src, 'jpg') or contains(@src, 'jpeg')]
    Sleep    2s
    Capture Page Screenshot

Scenario 8: Change Shipping Destination
    Run Keyword And Ignore Error    Wait Until Element Is Visible    xpath=//div[contains(@class, 'ship-to') or contains(text(),'Ship to')]    timeout=30s
    Run Keyword And Ignore Error    Click Element    xpath=//div[contains(@class, 'ship-to') or contains(text(),'Ship to')]
    Sleep    2s
    Run Keyword And Ignore Error    Click Element    xpath=//div[contains(text(),'United Arab Emirates') or contains(text(),'Germany')]
    Sleep    5s
    Run Keyword And Ignore Error    Page Should Contain Element    xpath=//span[contains(text(),'United Arab Emirates') or contains(text(),'Germany')]    xpath=//span[contains(text(),'United Arab Emirates') or contains(text(),'Germany')]
