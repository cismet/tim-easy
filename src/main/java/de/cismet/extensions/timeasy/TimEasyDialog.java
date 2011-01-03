/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
/*
 * TimEasyDialog.java
 *
 * Created on 6. November 2006, 10:23
 */
package de.cismet.extensions.timeasy;

import Sirius.navigator.connection.SessionManager;
import Sirius.navigator.types.treenode.RootTreeNode;
import Sirius.navigator.ui.ComponentRegistry;

import Sirius.server.localserver.attribute.ObjectAttribute;
import Sirius.server.middleware.types.MetaClass;
import Sirius.server.middleware.types.MetaObject;
import Sirius.server.middleware.types.MetaObjectNode;
import Sirius.server.newuser.permission.Policy;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

import java.rmi.Remote;

import java.text.DateFormat;

import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import de.cismet.cismap.commons.features.Feature;
import de.cismet.cismap.commons.gui.MappingComponent;

import de.cismet.tools.gui.StaticSwingTools;
//import net.java.swingfx.waitwithstyle.InfiniteProgressPanel;

/**
 * DOCUMENT ME!
 *
 * @author   thorsten.hell@cismet.de
 * @version  $Revision$, $Date$
 */
public class TimEasyDialog extends javax.swing.JDialog {

    //~ Static fields/initializers ---------------------------------------------

    private static Remote cidsRemoteService;
    private static String user;
    private static Vector<TimEasyListener> listeners = new Vector<TimEasyListener>();

    //~ Instance fields --------------------------------------------------------

    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
    private TimEasyPureNewFeature feature;
    private MappingComponent mappingComponent;
    private String classId = java.util.ResourceBundle.getBundle("de/cismet/extensions/timeasy/Bundle")
                .getString("classID");
    private String domainserver = java.util.ResourceBundle.getBundle("de/cismet/extensions/timeasy/Bundle")
                .getString("domainserver");
    private int parentNodeId = new Integer(java.util.ResourceBundle.getBundle("de/cismet/extensions/timeasy/Bundle")
                    .getString("parentNodeId")).intValue();
    // InfiniteProgressPanel glassPane;
    private Date dateCurrent;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdOk;
    private javax.swing.JLabel lblBearbeiter;
    private javax.swing.JLabel lblBemerkung;
    private javax.swing.JLabel lblDatum;
    private javax.swing.JProgressBar prbStore;
    private javax.swing.JTextField txtBearbeiter;
    private javax.swing.JTextField txtBemerkung;
    private javax.swing.JTextField txtDatum;
    // End of variables declaration//GEN-END:variables

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates new form TimEasyDialog.
     *
     * @param  parent            DOCUMENT ME!
     * @param  modal             DOCUMENT ME!
     * @param  feature           DOCUMENT ME!
     * @param  mappingComponent  DOCUMENT ME!
     */
    public TimEasyDialog(final java.awt.Frame parent,
            final boolean modal,
            final TimEasyPureNewFeature feature,
            final MappingComponent mappingComponent) {
        super(parent, modal);
        try {
            this.feature = feature;
            this.mappingComponent = mappingComponent;
//            glassPane=new InfiniteProgressPanel("TIM Objekt wird angelegt");
//            glassPane.setSize(getContentPane().getSize());
//            glassPane.setVisible(false);
            // this.setGlassPane(glassPane);
            initComponents();
            prbStore.setVisible(false);
            if (user == null) {
                try {
                    user = SessionManager.getSession().getUser().getName() + "@"
                                + SessionManager.getSession().getUser().getUserGroup().getName();
                } catch (Exception e) {
                    if (log.isDebugEnabled()) {
                        log.debug("Nicht innerhalb des Navigators ge\u00F6ffnet");
                    }
                }
            }
            dateCurrent = new Date(System.currentTimeMillis());
            txtBearbeiter.setText(user);
            final DateFormat format = DateFormat.getDateTimeInstance();
            txtDatum.setText(format.format(dateCurrent));
            txtBemerkung.requestFocus();
            getRootPane().setDefaultButton(cmdOk);
        } catch (Exception e) {
            log.error("Fehler im Konstruktor von TimeEasyDialog", e);
        }
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblBearbeiter = new javax.swing.JLabel();
        lblDatum = new javax.swing.JLabel();
        lblBemerkung = new javax.swing.JLabel();
        txtBearbeiter = new javax.swing.JTextField();
        txtBemerkung = new javax.swing.JTextField();
        txtDatum = new javax.swing.JTextField();
        cmdOk = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        prbStore = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(
                "de/cismet/extensions/timeasy/Bundle");                              // NOI18N
        setTitle(bundle.getString("TimEasyDialog.title"));                           // NOI18N
        lblBearbeiter.setText(bundle.getString("TimEasyDialog.lblBearbeiter.text")); // NOI18N

        lblDatum.setText(bundle.getString("TimEasyDialog.lblDatum.text")); // NOI18N

        lblBemerkung.setText(bundle.getString("TimEasyDialog.lblBemerkung.text")); // NOI18N

        txtBearbeiter.setEditable(false);
        txtBearbeiter.setText(bundle.getString("TimEasyDialog.txtBearbeiter.text")); // NOI18N

        txtBemerkung.setText(bundle.getString("TimEasyDialog.txtBemerkung.text")); // NOI18N
        txtBemerkung.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    txtBemerkungActionPerformed(evt);
                }
            });

        txtDatum.setEditable(false);
        txtDatum.setText(bundle.getString("TimEasyDialog.txtDatum.text")); // NOI18N

        cmdOk.setText(bundle.getString("TimEasyDialog.cmdOk.text")); // NOI18N
        cmdOk.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmdOkActionPerformed(evt);
                }
            });

        cmdCancel.setText(bundle.getString("TimEasyDialog.cmdCancel.text")); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(final java.awt.event.ActionEvent evt) {
                    cmdCancelActionPerformed(evt);
                }
            });

        prbStore.setBorderPainted(false);
        prbStore.setIndeterminate(true);
        prbStore.setString(bundle.getString("TimEasyDialog.prbStore.string")); // NOI18N
        prbStore.setStringPainted(true);

        final org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
                layout.createSequentialGroup().addContainerGap().add(
                    layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
                        layout.createSequentialGroup().add(lblBearbeiter).add(14, 14, 14).add(
                            txtBearbeiter,
                            org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                            181,
                            Short.MAX_VALUE)).add(
                        layout.createSequentialGroup().add(lblDatum).add(33, 33, 33).add(
                            txtDatum,
                            org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                            181,
                            Short.MAX_VALUE)).add(
                        layout.createSequentialGroup().add(lblBemerkung).add(11, 11, 11).add(
                            txtBemerkung,
                            org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                            181,
                            Short.MAX_VALUE)).add(
                        org.jdesktop.layout.GroupLayout.TRAILING,
                        layout.createSequentialGroup().add(
                            prbStore,
                            org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                            org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                            org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                            org.jdesktop.layout.LayoutStyle.RELATED).add(cmdCancel).addPreferredGap(
                            org.jdesktop.layout.LayoutStyle.RELATED).add(cmdOk))).addContainerGap()));
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
                layout.createSequentialGroup().addContainerGap().add(
                    layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(lblBearbeiter).add(
                        txtBearbeiter,
                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                    org.jdesktop.layout.LayoutStyle.RELATED).add(
                    layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(lblDatum).add(
                        txtDatum,
                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                    org.jdesktop.layout.LayoutStyle.RELATED).add(
                    layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(lblBemerkung).add(
                        txtBemerkung,
                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                    org.jdesktop.layout.LayoutStyle.RELATED).add(
                    layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
                        layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(cmdOk).add(
                            cmdCancel)).add(
                        prbStore,
                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addContainerGap()));
        pack();
    } // </editor-fold>//GEN-END:initComponents

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmdCancelActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmdCancelActionPerformed
        dispose();
    }                                                                             //GEN-LAST:event_cmdCancelActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void cmdOkActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmdOkActionPerformed
        new Thread() {

                @Override
                public void run() {
                    try {
                        if (cidsRemoteService == null) {
                            SwingUtilities.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        cmdCancel.setEnabled(false);
                                        cmdOk.setEnabled(false);
                                        setEnabled(false);
                                        prbStore.setVisible(true);
//glassPane.setVisible(false);
                                    }
                                });

                            // glassPane.start();
                            final MetaClass mc = SessionManager.getProxy().getMetaClass(classId + "@" + domainserver);
                            if (log.isDebugEnabled()) {
                                log.debug("MetaClass: " + mc);
                            }
                            final MetaObject mo = SessionManager.getProxy().getInstance(mc);
                            if (log.isDebugEnabled()) {
                                log.debug("MetaObject: " + mo);
                                log.debug("MetaObject is null: " + (mo == null));
                                log.debug("MetaObject.getAttributes:" + mo.getAttributes());
                            }

                            final String name = txtBemerkung.getText() + " (" + txtBearbeiter.getText() + ")";
                            final ObjectAttribute nameAttr = ((ObjectAttribute)
                                    mo.getAttributeByName("Name", 1).toArray()[0]);
                            nameAttr.setValue(name);

                            final ObjectAttribute bearbeiter = ((ObjectAttribute)
                                    mo.getAttributeByName("angelegt von", 1).toArray()[0]);
                            bearbeiter.setValue(txtBearbeiter.getText());

                            final ObjectAttribute datum = ((ObjectAttribute)
                                    mo.getAttributeByName("angelegt am", 1).toArray()[0]);

                            datum.setValue(new java.sql.Date(dateCurrent.getTime()));

                            final ObjectAttribute bemerkung = ((ObjectAttribute)
                                    mo.getAttributeByName("hinweise", 1).toArray()[0]);
                            bemerkung.setValue(txtBemerkung.getText());

                            final ObjectAttribute alkis = ((ObjectAttribute)
                                    mo.getAttributeByName("ALKIS", 1).toArray()[0]);
                            alkis.setValue(null);

                            final ObjectAttribute ka = ((ObjectAttribute)
                                    mo.getAttributeByName("Katographie", 1).toArray()[0]);
                            ka.setValue(null);

                            final ObjectAttribute georeferenz = ((ObjectAttribute)
                                    mo.getAttributeByName("georeferenz", 1).toArray()[0]);
                            final MetaObject geoMo = (MetaObject)georeferenz.getValue();
                            final ObjectAttribute geometry = ((ObjectAttribute)
                                    geoMo.getAttributeByName("geo_string", 1).toArray()[0]);
                            geometry.setValue(feature.getGeometry());
                            geoMo.setStatus(MetaObject.NEW);

                            mo.setChanged(true);
                            final MetaObject newMo = SessionManager.getProxy().insertMetaObject(mo, domainserver);

                            final int id = newMo.getID();
                            final MetaObjectNode metaObjectNode = new MetaObjectNode(
                                    -1,
                                    domainserver,
                                    newMo,
                                    txtBemerkung.getText()
                                            + " ("
                                            + txtBearbeiter.getText()
                                            + ")",
                                    null,
                                    true,
                                    Policy.createWIKIPolicy(),
                                    -1,
                                    null,
                                    true);
//                        SessionManager.getProxy().addNode(metaObjectNode,new Sirius.server.middleware.types.Link(parentNodeId,domainserver));
//

                            try {
                                final TreePath selectionPath = ComponentRegistry.getRegistry()
                                            .getCatalogueTree()
                                            .getSelectionPath();

                                final RootTreeNode rootTreeNode = new RootTreeNode(SessionManager.getProxy()
                                                .getRoots());

                                ((DefaultTreeModel)ComponentRegistry.getRegistry().getCatalogueTree().getModel())
                                        .setRoot(rootTreeNode);
                                ((DefaultTreeModel)ComponentRegistry.getRegistry().getCatalogueTree().getModel())
                                        .reload();

                                ComponentRegistry.getRegistry().getCatalogueTree().exploreSubtree(selectionPath);
                            } catch (Exception e) {
                            }
                            fireTimEasyObjectInserted(feature, metaObjectNode);
                        }
                    } catch (Throwable t) {
                        log.error("Fehler", t);
                        final ErrorInfo ei = new ErrorInfo(
                                "Fehler.",
                                "Tim Easy Fehler",
                                null,
                                null,
                                t,
                                Level.SEVERE,
                                null);
                        JXErrorPane.showDialog(StaticSwingTools.getParentFrame(TimEasyDialog.this), ei);
                    } finally {
                        SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    dispose();
                                }
                            });
                    }
                }
            }.start();
    } //GEN-LAST:event_cmdOkActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @param  evt  DOCUMENT ME!
     */
    private void txtBemerkungActionPerformed(final java.awt.event.ActionEvent evt) { //GEN-FIRST:event_txtBemerkungActionPerformed
// TODO add your handling code here:
    } //GEN-LAST:event_txtBemerkungActionPerformed

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static String getUser() {
        return user;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  aUser  DOCUMENT ME!
     */
    public static void setUser(final String aUser) {
        user = aUser;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Remote getCidsRemoteService() {
        return cidsRemoteService;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  aCidsRemoteService  DOCUMENT ME!
     */
    public static void setCidsRemoteService(final Remote aCidsRemoteService) {
        cidsRemoteService = aCidsRemoteService;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  originalPureFeature  DOCUMENT ME!
     * @param  newNode              DOCUMENT ME!
     */
    protected void fireTimEasyObjectInserted(final Feature originalPureFeature, final MetaObjectNode newNode) {
        final TimEasyEvent tee = new TimEasyEvent();
        tee.setPureNewfeature(originalPureFeature);
        tee.setMetaObjectNode(newNode);
        for (final TimEasyListener tel : listeners) {
            tel.timEasyObjectInserted(tee);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  tel  DOCUMENT ME!
     */
    public static void addTimTimEasyListener(final TimEasyListener tel) {
        listeners.add(tel);
    }
    /**
     * DOCUMENT ME!
     *
     * @param  tel  DOCUMENT ME!
     */
    public static void removeTimTimEasyListener(final TimEasyListener tel) {
        listeners.remove(tel);
    }
}
