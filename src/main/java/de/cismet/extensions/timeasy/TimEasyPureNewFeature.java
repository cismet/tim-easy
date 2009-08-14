/*
 * TimEasyPureNewFeature.java
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
 * Created on 6. November 2006, 09:53
 *
 */

/**
 *
 * @author thorsten.hell@cismet.de
 */

package de.cismet.extensions.timeasy;


import Sirius.navigator.connection.SessionManager;
import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.Node;
import Sirius.server.newuser.permission.Permission;
import Sirius.server.newuser.permission.PermissionHolder;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import de.cismet.cismap.commons.WorldToScreenTransform;
import de.cismet.cismap.commons.features.InputEventAwareFeature;
import de.cismet.cismap.commons.features.PureNewFeature;
import de.cismet.cismap.commons.features.XStyledFeature;
import de.cismet.cismap.commons.gui.MappingComponent;
import de.cismet.cismap.commons.gui.piccolo.PFeature;
import de.cismet.cismap.commons.tools.PFeatureTools;
import de.cismet.tools.gui.StaticSwingTools;
import edu.umd.cs.piccolo.event.PInputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.geom.Point2D;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class TimEasyPureNewFeature extends PureNewFeature implements InputEventAwareFeature{
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
    private String classId=java.util.ResourceBundle.getBundle("de/cismet/extensions/timeasy/Bundle").getString("classID");
    private String domainserver=java.util.ResourceBundle.getBundle("de/cismet/extensions/timeasy/Bundle").getString("domainserver");
    private int parentNodeId=new Integer(java.util.ResourceBundle.getBundle("de/cismet/extensions/timeasy/Bundle").getString("parentNodeId")).intValue(); 
    
    public TimEasyPureNewFeature(Geometry g) {
        super(g);
    }
    public TimEasyPureNewFeature(Point2D point,WorldToScreenTransform wtst) {
        super(point,wtst);
    }
    public TimEasyPureNewFeature(Point2D[] canvasPoints,WorldToScreenTransform wtst) {
        super(canvasPoints,wtst);
    }
    public TimEasyPureNewFeature(Coordinate[] coordArr,WorldToScreenTransform wtst) {
        super(coordArr,wtst);
    }
    
    public boolean noFurtherEventProcessing(PInputEvent event) {
        return true;
    }
    
    public void mouseWheelRotated(PInputEvent event) {
    }
    
    public void mouseReleased(PInputEvent event) {
    }
    
    public void mousePressed(PInputEvent event) {
    }
    
    public void mouseMoved(PInputEvent event) {
    }
    
    public void mouseExited(PInputEvent event) {
    }
    
    public void mouseEntered(PInputEvent event) {
    }
    
    public void mouseDragged(PInputEvent event) {
    }
    
    public void mouseClicked(PInputEvent event) {
        try {
            event.setHandled(noFurtherEventProcessing(event));
            MetaClass metaClass=SessionManager.getProxy().getMetaClass(classId+"@"+domainserver);
            String userkey=SessionManager.getSession().getUser().getUserGroup().getKey().toString();
            Permission permission=PermissionHolder.WRITEPERMISSION;
            boolean writePermission=metaClass.getPermissions().hasPermission(userkey, permission);
            //Node parentNode=SessionManager.getProxy().getNode(parentNodeId,domainserver);
            
            final boolean timLiegPermission = metaClass.getPermissions().hasWritePermission(SessionManager.getSession().getUser().getUserGroup());
            Object o=PFeatureTools.getFirstValidObjectUnderPointer(event, new Class[]{PFeature.class});
            if (event.getComponent() instanceof MappingComponent) {
                final  MappingComponent mc=(MappingComponent)event.getComponent();
                
                if (event.getModifiers()==InputEvent.BUTTON3_MASK){//&&writePermission) {
                    log.debug("right mouseclick");
                    if (o instanceof PFeature && ((PFeature)o).getFeature() instanceof XStyledFeature) {
                        XStyledFeature xf=(XStyledFeature)((PFeature)o).getFeature();
                        log.debug("valid object under pointer");
                        JPopupMenu popup=new JPopupMenu("Test");
                        JMenuItem m=new JMenuItem("TIM Merker anlegen");
                        m.setIcon(xf.getIconImage());
                        m.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                log.debug("TIM Action performed");
                                log.info("permission an TimLieg:"+timLiegPermission);
                                if (true||timLiegPermission) {
                                    TimEasyDialog ted=new TimEasyDialog(StaticSwingTools.getParentFrame(mc),true,TimEasyPureNewFeature.this,mc);
                                    ted.pack();
                                    ted.setLocationRelativeTo(mc);
                                    ted.setVisible(true);
                                }
                                else{
                                    JOptionPane.showMessageDialog(StaticSwingTools.getParentFrame(mc),java.util.ResourceBundle.getBundle("de/cismet/extensions/timeasy/Bundle").getString("keine_rechte_am_parentnode"),"TimEasy Fehler",JOptionPane.ERROR_MESSAGE);
                                }
                                    
                            }
                        });
                        popup.add(m);
                        popup.show(mc,(int)event.getCanvasPosition().getX(),(int)event.getCanvasPosition().getY());
                    }
                }
            }
        } catch (Throwable t) {
            log.error("Fehler beim Aufruf des TIM Easy Dialog.",t);
        }
    }
}
