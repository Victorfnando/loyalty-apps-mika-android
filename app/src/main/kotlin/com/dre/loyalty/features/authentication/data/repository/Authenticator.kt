/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dre.loyalty.features.authentication.data.repository

import com.dre.loyalty.core.platform.util.preferences.AuthenticationManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator @Inject constructor(
    private val authenticationManager: AuthenticationManager
){
    //Learning purpose: We assume the user is always logged in
    //Here you should put your own logic to return whether the user
    //is authenticated or not
    fun userLoggedIn(): Boolean = authenticationManager.getUserId() != null
            && authenticationManager.getToken() != null
}
