package br.cassio.devmedia.firebase_messenger.models

/**
 * classe que representa o usuario
 *
 * @property uid
 * @property username
 * @property profileImageUrl
 */
class User(val uid: String, val username: String, val profileImageUrl: String){
    constructor(): this("","","" )
}