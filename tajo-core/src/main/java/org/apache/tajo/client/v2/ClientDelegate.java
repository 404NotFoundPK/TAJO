/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tajo.client.v2;

import org.apache.tajo.exception.QueryFailedException;
import org.apache.tajo.exception.UndefinedDatabaseException;
import org.apache.tajo.exception.TajoException;

import java.io.Closeable;
import java.sql.ResultSet;

/**
 * ClientDelegate is a delegate for various wired protocols like protocol buffer, rest API, and proxy.
 */
public interface ClientDelegate extends Closeable {

  int executeUpdate(String sql) throws TajoException;

  ResultSet executeSQL(String sql) throws TajoException, QueryFailedException;

  QueryFuture executeSQLAsync(String sql) throws TajoException;

  String currentDB();

  void selectDB(String db) throws UndefinedDatabaseException;
}
