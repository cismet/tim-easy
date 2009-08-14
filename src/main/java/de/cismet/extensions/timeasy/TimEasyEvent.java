/*
 * TimEasyEvent.java
 * Copyright (C) 2005 by:
 *
 *----------------------------
 * cismet GmbH
 * Goebenstrasse 40
 * 66117 Saarbruecken
 * http://www.cismet.de
 *----------------------------
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *----------------------------
 * Author:
 * thorsten.hell@cismet.de
 *----------------------------
 *
 * Created on 8. November 2006, 14:32
 *
 */

package de.cismet.extensions.timeasy;

import Sirius.server.middleware.types.MetaObjectNode;
import de.cismet.cismap.commons.features.Feature;

/**
 *
 * @author thorsten.hell@cismet.de
 */
public class TimEasyEvent {
    private Feature pureNewfeature;
    private MetaObjectNode metaObjectNode;
            
    /** Creates a new instance of TimEasyEvent */
    public TimEasyEvent() {
    }

    public Feature getPureNewfeature() {
        return pureNewfeature;
    }

    public void setPureNewfeature(Feature pureNewfeature) {
        this.pureNewfeature = pureNewfeature;
    }

    public MetaObjectNode getMetaObjectNode() {
        return metaObjectNode;
    }

    public void setMetaObjectNode(MetaObjectNode metaObjectNode) {
        this.metaObjectNode = metaObjectNode;
    }
    
}
