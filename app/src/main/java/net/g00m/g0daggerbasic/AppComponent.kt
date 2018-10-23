package net.g00m.g0daggerbasic

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppContext

@Singleton
@Component(
    modules = arrayOf(
        ActivityModule::class,
        AppModule::class,
        PrefModule::class
    )
)

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}

@Module(includes = arrayOf(AndroidSupportInjectionModule::class))
abstract class AppModule {

    @Singleton
    @Binds
    @AppContext
    abstract fun provideContext(app: App): Context
}

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivityInjector(): MainActivity
}

@Module
open class PrefModule {
    @Singleton
    @Provides
    fun providePrefs(@AppContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

}