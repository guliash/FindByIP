# Moshi
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}

#Retrofit
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-dontwarn javax.annotation.**
-keepattributes Signature
-keepattributes Exceptions


#Okhttp
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault