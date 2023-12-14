MultipleActivityApp:

Code:

Architecture Followed : MVVM
Network Call: Retrofit and RxJava
Languages used: Kotlin
All the main Strings are written in strings.xml


Three Activities 

Activity 1 :

	HomePage:
		It is a product listing page that is having grid layout divided to two columns.
		We can go to product details by clicking on the item.
		We can add the product to the cart bt clicking on + icon.
		We can perform search based on the product name.
		Data is coming from viewmodel which is LiveData and we are observing in the HomePageFragment.

	CartPage:
		It is a product listing for the products that are added to the cart.
		We can delete the products from the cart by clicking on cross icon.

Activity 2 :

	Product Details Page:
		It is a single product details page with Product details like name, image and price,etc.
		Add to Cart will add the product in the cart.
 	        Click on Product Page button(Present at the bottom of the page) -> This will open WebView

Activity 3 :

	WebView Page:
 		GoTo Product details Page -> Click on Product Page button(Present at the bottom of the page) -> This will open WebView 
		It's a product information page that will open in Web View.
		Since the data from API was not proper. It will only be loading same page for all the items.
