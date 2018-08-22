package app.pashmak.com.pashmak.data.source.cloud

import app.pashmak.com.pashmak.data.restful.APIs
import app.pashmak.com.pashmak.data.restful.APIsWithToken

/**
 * The main implementation of [BaseCloudRepository] that call api services directly
 * @param apIs instance of without-token apis
 * @param apIsWithToken instance of with-token apis
 */
class CloudRepository(private val apIs: APIs, private val apIsWithToken: APIsWithToken) : BaseCloudRepository {

}
