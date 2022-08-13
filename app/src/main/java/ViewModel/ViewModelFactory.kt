package ViewModel

import repository.Repository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// returns the SignUpActivityViewModel
class SignUpScreenViewModelFactory(private val repository:Repository):
ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpScreenViewModel(repository) as T
    }
}
//viewModelFactory to return the OtpScreen View Model
class OtpScreenViewModelFactory(private val repository: Repository):ViewModelProvider.Factory{
    override fun <T:ViewModel?> create(modelClass: Class<T>):T{
        return OtpScreenViewModel(repository) as T
    }
}
// returns the LoginScreenViewModel
class LoginScreenViewModelFactory(private val repository:Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginScreenViewModel(repository) as T
    }
}

// ViewModeFactory to return BuyerScreenViewModel
class BuyerHomeScreenViewModelFactory(private val repository:Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BuyerHomeScreenViewModel(repository) as T
    }
}
// viewModelFactory to return the SellerHomeScreenViewModel
class SellerHomeScreenViewModelFactory(private val repository:Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SellerHomeScreenViewModel(repository) as T
    }
}
// ViewModelFactory to return the productsctivityViewModel
class ProductsActivityViewModelFactory(private val repository:Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsActivityViewModel(repository) as T
    }
}

    // ViewModelFactory to return the forgotPasswordViewModel
    class ForgotPasswordActivityViewModelFactory(private val repository:Repository):
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ForgotPasswordActivityViewModel(repository) as T
        }
    }

    //viewModelFactory to return the CartListViewModel
    class CartListScreenViewModelFactory(private val repository: Repository):ViewModelProvider.Factory{
        override fun <T:ViewModel?> create(modelClass: Class<T>):T{
            return CartListScreenViewModel(repository) as T
        }
    }
//viewModelFactory to return the productDetailScreen View Model
class ProductDetailScreenViewModelFactory(private val repository: Repository):ViewModelProvider.Factory{
    override fun <T:ViewModel?> create(modelClass: Class<T>):T{
        return ProductDetailScreenViewModel(repository) as T
    }
}