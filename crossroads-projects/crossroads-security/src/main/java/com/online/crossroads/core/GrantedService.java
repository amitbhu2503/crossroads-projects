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

package com.online.crossroads.core;

import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Represents an service granted to an {@link Authentication} object.
 * <p/>
 * <p/>
 * A <code>GrantedService</code> must either represent itself as a
 * <code>String</code>
 * <p/>
 *
 * @author amitg
 */
public interface GrantedService extends Serializable {

    /**
     * @return a representation of the granted service (or <code>null</code> if the granted service cannot be
     * expressed as a <code>String</code> with sufficient precision).
     */
    String getService();

}
