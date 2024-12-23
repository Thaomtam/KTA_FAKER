
        package com.example.hookfaker

        import android.os.Build
        import com.highcapable.yukihookapi.hook.HookEntry

        class MainHook : HookEntry() {
            override fun onHook() {
                hookClass(Build::class.java.name) {
                    injectMember {
                        method { name = "MODEL" }
                        replaceTo("Fake_Model_${System.currentTimeMillis()}")
                    }
                    injectMember {
                        method { name = "MANUFACTURER" }
                        replaceTo("Fake_Manufacturer")
                    }
                }
            }
        }
    