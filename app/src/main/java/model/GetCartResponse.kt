package model

data class GetCartResponse(
    val cartItems: List<CartItemX>,
    val totalCost: Int
)