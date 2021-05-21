# REST API Endpoints

	http://localhost:8080
		
		/auth
			POST /register - Register - required: email, username, password
			POST /login - Login - required: username, password

		/product
			GET /get - Get all the products 
			GET /getid/{id} - Get a specific product by its id
			GET /getname/{name} - Get a list of prodcut by their names
			POST /add - Add a product - required: name, price, description

		/order
			GET /get - Get all the orders for the current logged in user
			POST /addtocart - Make an order and add the product to the cart - 
						required: product id, full name, address, city, zip code, phone 

		/edit
			PUT /user - Edit the current logged in user info - required: email, username, password
			PUT /product/{id} - Edit a specific product info - required: name, price, description
			DELETE /order/{id} - Cancel an order