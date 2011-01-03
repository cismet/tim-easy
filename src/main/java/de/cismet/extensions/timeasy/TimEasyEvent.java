/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.extensions.timeasy;

import Sirius.server.middleware.types.MetaObjectNode;

import de.cismet.cismap.commons.features.Feature;

/**
 * DOCUMENT ME!
 *
 * @author   thorsten.hell@cismet.de
 * @version  $Revision$, $Date$
 */
public class TimEasyEvent {

    //~ Instance fields --------------------------------------------------------

    private Feature pureNewfeature;
    private MetaObjectNode metaObjectNode;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new instance of TimEasyEvent.
     */
    public TimEasyEvent() {
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public Feature getPureNewfeature() {
        return pureNewfeature;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  pureNewfeature  DOCUMENT ME!
     */
    public void setPureNewfeature(final Feature pureNewfeature) {
        this.pureNewfeature = pureNewfeature;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public MetaObjectNode getMetaObjectNode() {
        return metaObjectNode;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  metaObjectNode  DOCUMENT ME!
     */
    public void setMetaObjectNode(final MetaObjectNode metaObjectNode) {
        this.metaObjectNode = metaObjectNode;
    }
}
