package net.g00m.g0daggerbasic

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class App: DaggerApplication() {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .create(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityDispatchingAndroidInjector;
    //override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}