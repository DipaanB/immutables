/*
 * Copyright 2019 Immutables Authors and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.immutables.criteria.repository.async;

import org.immutables.criteria.backend.Backend;
import org.immutables.criteria.backend.ProjectedTuple;
import org.immutables.criteria.expression.Query;
import org.immutables.criteria.repository.MapperFunction3;
import org.immutables.criteria.repository.Mappers;
import org.immutables.criteria.repository.reactive.ReactiveFetcher;

public class AsyncMapper3<T1, T2, T3> {

  private final Query query;
  private final Backend.Session session;

  AsyncMapper3(Query query, Backend.Session session) {
    this.query = query;
    this.session = session;
  }

  public <R> AsyncFetcher<R> map(MapperFunction3<T1, T2, T3, R> mapFn) {
    ReactiveFetcher<R> delegate = new ReactiveFetcher<ProjectedTuple>(query, session).map(Mappers.fromTuple(mapFn));
    return new AsyncFetcher<R>(delegate);
  }
}