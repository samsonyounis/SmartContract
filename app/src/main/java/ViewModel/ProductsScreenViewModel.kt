package ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import model.ProductList
import repository.Repository
import retrofit2.Call

// viewModel for the products screen/view
class ProductsActivityViewModel(private val repository: Repository): ViewModel() {
    //Function to fetch list of products
    suspend fun getProductList(): Call<List<ProductList>> {
        return repository.getProductList()
    }
}

// ViewModelFactory to return the productsctivityViewModel
class ProductsActivityViewModelFactory(private val repository:Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsActivityViewModel(repository) as T
    }
}