package fer.kotlin.weatherapp.utils

import android.content.Context
import fer.kotlin.weatherapp.R
import okhttp3.OkHttpClient
import java.io.BufferedInputStream
import java.io.IOException
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory

import java.util.*
import javax.net.ssl.*


/**
 * Devuelve un SSLSocketFactory que acepta el certificado ubicado en assets/FICHERO_CERT como
 * certificado de confianza

 */
object CustomSSLSocketFactory {

        private var okkHttpSSLClient: OkHttpClient? = null

        @Throws(CertificateException::class, IOException::class, GeneralSecurityException::class)
        fun getSSLSocketFactory(context: Context): OkHttpClient? {
            //sólo se instancia la primera vez que se necesite
            if (okkHttpSSLClient == null) {
                val cf = CertificateFactory.getInstance("X.509")
                val caInput = BufferedInputStream(context
                        .resources.openRawResource(R.raw.my_ca))
                val ca: Certificate
                try {
                    ca = cf.generateCertificate(caInput)
                } finally {
                    caInput.close()
                }

                //se añaden todos los certificados obtenidos desde el assets. En este caso
                //sólo tenemos uno.
                val keyStoreType = KeyStore.getDefaultType()
                val keyStore = KeyStore.getInstance(keyStoreType)
                keyStore.load(null, null)
                keyStore.setCertificateEntry("ca", ca)

                // Create a TrustManager that trusts the CAs in our KeyStore
                val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
                val tmf = TrustManagerFactory
                        .getInstance(tmfAlgorithm)
                tmf.init(keyStore)

                val trustManagers = tmf.trustManagers
                if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
                    throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
                }
                val trustManager = trustManagers[0] as X509TrustManager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
                val sslSocketFactory = sslContext.socketFactory

                /* Return OkHttpClient with TrustManager containing the CAs in the store */
                okkHttpSSLClient = OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, trustManager).build()
            }
            return okkHttpSSLClient
        }
}