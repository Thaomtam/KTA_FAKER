
package com.hujiayucc.hook.hook

import android.os.Build
import android.telephony.TelephonyManager
import android.net.ConnectivityManager
import java.util.UUID
import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.PackageParam

/** HookEntry mới với mục đích giả mạo thông tin Android */
@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {
    private val TAG = "HookEntry"

    override fun onInit() = configs {
        debugLog {
            tag = "HookFaker"
            isEnable = BuildConfig.DEBUG
            isRecord = true
        }
        isDebug = BuildConfig.DEBUG
    }

    override fun onHook() = YukiHookAPI.encase {
        if (YukiHookAPI.Status.isModuleActive && packageName != BuildConfig.APPLICATION_ID && packageName != "android") {
            YLog.info(tag = TAG, msg = "onHook activated for package: $packageName")
            loadApp(packageName) {
                applyFakerHooks(this)
            }
        }
    }

    /** Thêm logic faker vào package */
    private fun applyFakerHooks(packageParam: PackageParam) {
        packageParam.hook {
            // Giả mạo thông tin từ android.os.Build
            injectMember {
                method {
                    name = "getBrand"
                    declaringClass = Build::class.java
                }
                replaceTo { "FakeBrand" }
            }
            injectMember {
                method {
                    name = "getModel"
                    declaringClass = Build::class.java
                }
                replaceTo { "FakeModel" }
            }
            injectMember {
                method {
                    name = "getManufacturer"
                    declaringClass = Build::class.java
                }
                replaceTo { "FakeManufacturer" }
            }

            // Giả mạo thông tin từ TelephonyManager
            injectMember {
                method {
                    name = "getDeviceId"
                    declaringClass = TelephonyManager::class.java
                }
                replaceTo { "1234567890" }
            }
            injectMember {
                method {
                    name = "getSubscriberId"
                    declaringClass = TelephonyManager::class.java
                }
                replaceTo { "9876543210" }
            }

            // Giả mạo UUID
            injectMember {
                method {
                    name = "randomUUID"
                    declaringClass = UUID::class.java
                }
                replaceTo { UUID.fromString("123e4567-e89b-12d3-a456-426614174000") }
            }

            // Giả mạo thông tin mạng từ ConnectivityManager
            injectMember {
                method {
                    name = "getActiveNetworkInfo"
                    declaringClass = ConnectivityManager::class.java
                }
                replaceTo { null } // Giả lập không có kết nối mạng
            }
        }

        YLog.info(tag = TAG, msg = "All faker hooks applied for $packageName")
    }
}
