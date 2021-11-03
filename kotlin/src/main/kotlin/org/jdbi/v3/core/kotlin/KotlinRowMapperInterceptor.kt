/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdbi.v3.core.kotlin

import org.jdbi.v3.core.interceptor.JdbiInterceptionChain
import org.jdbi.v3.core.interceptor.JdbiInterceptor
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.mapper.RowMapperFactory

class KotlinRowMapperInterceptor : JdbiInterceptor<RowMapper<*>, RowMapperFactory> {

    override fun intercept(source: RowMapper<*>?, chain: JdbiInterceptionChain<RowMapper<*>, RowMapperFactory>): RowMapperFactory {
        return if (source is KotlinMapper) {
            RowMapperFactory.of(source.kClass.java, source)
        } else {
            chain.next()
        }
    }
}
