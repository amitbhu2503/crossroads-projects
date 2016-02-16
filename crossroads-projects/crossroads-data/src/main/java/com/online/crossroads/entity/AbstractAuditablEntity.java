/*
 *
 *  * Copyright ©2015  The Aleph Group PTE limited ( ALEPH). Copyright protection includes content in the material generated from the software program. All information contained in the material is confidential and proprietary to ALEPH.   Any attempt to copy, reproduce or modify and re-distribute this software and its documentation without express prior written permission is expressly prohibited and may result in sever civil and criminal penalties.  Use of the Software is governed by the terms of the end user license agreement, if any, which accompanies or is included with the Software ("License Agreement") .Contact eng-operations@culturemachine.in for commercial licensing opportunities. All Rights Reserved.
 *  *
 *  * These libraries and applications are trademarks or registered trademarks of The Aleph Group PTE Limited, in Singapore, United States of America and/or other countries. All other trademarks are the property of their respective owners.
 *  *
 *  * Technologies described herein may be covered by existing U.S. patents or U.S. patent applications that are in progress.
 *  *
 *  * IN NO EVENT SHALL ALEPH BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF ALEPH HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. ALEPH SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED HEREUNDER IS PROVIDED "AS IS". ALEPH HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 */

package com.online.crossroads.entity;


import com.online.crossroads.type.StatusType;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * Created by lenovo on 02-02-2016.
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class AbstractAuditablEntity
        extends AbstractPersistable<Long> {

    @Version
    @Column(name = "version")
    protected Long version;

    @Enumerated(EnumType.STRING)
    protected StatusType status;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime createdDate;

    @ManyToOne
    private User createdBy;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime lastModifiedDate;

    @ManyToOne
    private User lastModifiedBy;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @PrePersist
    public void setCreatedDate() {
        this.createdDate = new DateTime();
        this.lastModifiedDate = new DateTime();
    }

    @PreUpdate
    public void setLastModifiedDate() {
        this.lastModifiedDate = new DateTime();
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
