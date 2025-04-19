package com.example.filmmovie.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.filmmovie.R
import com.example.filmmovie.databinding.FragmentSplashBinding
import com.example.filmmovie.utils.StoreUserData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var storeUserData: StoreUserData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set delay
        lifecycle.coroutineScope.launchWhenCreated {
            delay(2000)
            //Check user token
            storeUserData.getUserToken().collect{
                /*if (it.isEmpty()){
                    findNavController().navigate(R.id.actionSplashToRegister)
                }else{*/
                    findNavController().navigate(R.id.actionToHome)
                //}
            }
        }
    }
}