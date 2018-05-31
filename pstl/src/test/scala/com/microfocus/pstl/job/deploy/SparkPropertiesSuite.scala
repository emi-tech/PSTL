/*
 * (c) Copyright [2018] Micro Focus or one of its affiliates.
 *
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

package com.microfocus.pstl.job.deploy

import java.io.{File, PrintWriter}
import java.nio.file.{Path, Paths}

import org.apache.spark.SparkFunSuite
import org.apache.spark.sql.test.SharedSQLContext
import org.scalatest.Matchers

class SparkPropertiesSuite extends SparkFunSuite with SharedSQLContext with Matchers {

  test("SparkProperties") {
    withTempDir { dir =>
      val file = new File(dir,"text.txt")
      val pw = new PrintWriter(file)
      pw.write("name=medha")
      pw.close()
      val providessparkproperties = new ProvidesSparkProperties {
        protected def sparkPropertiesPath: Path = Paths.get(file.getCanonicalPath)
      }
      println(providessparkproperties.sparkProperties)
      assert(providessparkproperties.sparkProperties == Map("name" -> "medha"))
    }
  }
}
