/*
 * Copyright(c) 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sastix.cms.server.domain.repositories;

import com.sastix.cms.server.domain.entities.Resource;
import com.sastix.cms.server.domain.entities.Revision;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RevisionRepository extends CrudRepository<Revision, Integer> {

    Revision findOneByResourceUidOrderByIdDesc(@Param("uid") String uid);

    List<Revision> findByResourceUidOrderByIdDesc(@Param("uid") String uid);


    List<Revision> findByParentResourceUidOrderByIdDesc(@Param("uid") String uid);

    @Query("SELECT r FROM Revision r, Resource res WHERE r.resource.id = res.id AND res.uid = :uid ORDER BY r.id desc")
    List<Revision> findRevisions(@Param("uid") String uid, Pageable pageable);


    @Query("SELECT r FROM Revision r WHERE r.resource.id = :resourceId AND r.archivedResource IS NOT NULL ORDER BY r.updatedAt DESC")
    List<Revision> findResourceRevisionsByResourceId(@Param("resourceId") Integer resourceId);

}
