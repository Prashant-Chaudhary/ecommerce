# Welcome to SAGGEZZA E-COMMERCE Service!

Hi! I'm **SAGGEZZA E-COMMERCE Service**. If you want to learn about this project, you can read this file. Basic Modules & Functionality are explained below.


# Modules

The basic modules are Product Catalog , Shopping Cart , Login

## How to Execute

Just Import the Repository in Eclipse of IntelliJ. Start the Server by running the **BackendApplication.java** file. Once the server has been started the sample REST APIs should be up and running at - **http://localhost:8080/swagger-ui.html** . 

## Steps to Follow for test cases

 1. Open the **http://localhost:8080/swagger-ui.html** URL.
 2. Use the **Authentication Controllers** and hit the login API. Sample Credentials can be procured from                  **https://reqres.in/**.
 **{
    "email": "eve.holt@reqres.in",
    "password": "12345"
    }**
 3. Copy the Token and use it with other APIs by Appending **Bearer TokenString**. TokenString is the token recieved from         Login API.
 4. Now add Products to the CART. If you want to add a new product use the below payload.
    **{
  "id": 0,
  "product_name": "string",
  "product_quantity": 0
    }**
    **If the id is 0, product would be treated as new product. It id is given in the payload then the system would update the       existing product with that id.**
 5. You can now try to hit **Get Catalog** API to the product catalog.
 6. Now add some products into cart using the **Shopping Cart** API. The systme automatically adds the item in the cart of the     current logged in Users.
 
 
 # Unable to Add Mockito Test Cases due to lack of time.

