package guillaume.agis.babylonhealth.di

import dagger.Binds
import dagger.Module
import guillaume.agis.babylonhealth.datastore.UsersDatastore
import guillaume.agis.babylonhealth.datastore.UsersDatastoreImpl

@Module
abstract class UserDatastoreModule {

    @Binds
    abstract fun bindUserDatastore(usersDatastore: UsersDatastoreImpl): UsersDatastore

}