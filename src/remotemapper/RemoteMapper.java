/*
 * Copyright (C) 2018 Robert Hutter
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package remotemapper;

import remotemapper.exceptions.SerialException;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.text.DefaultCaret;
import javax.swing.text.PlainDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import remoteMapper.classes.Map;
import remotemapper.classes.CommandPreset;
import remotemapper.classes.Coord;
import remotemapper.classes.Node;
import remotemapper.classes.Rover;
import remotemapper.exceptions.LoadWorkspaceException;
import remotemapper.utility.AngleFilter;
import remotemapper.utility.LengthFilter;
import remotemapper.utility.MyIntFilter;

/**
 * RemoteMapper.java - A class containing the most of the Remote Mapper GUI
 and some functions.
 * 
 * @author Robert Hutter
 */
public class RemoteMapper extends javax.swing.JFrame {
    private SerialHandler port;
    private Map map, simpleMap;
    private Rover rover;
    private File workspace, mapFile, presetFile;
    private CommandPreset[] presets;

    /**
     * Creates new form RemoteMapper
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public RemoteMapper() {
        initComponents();
        
        // Init custom components
        port = new SerialHandler ();
        wizardPage1.setLocationRelativeTo(null);
        wizardPage2.setLocationRelativeTo(null);
        wizardPage3.setLocationRelativeTo (null);
        wizardPage4.setLocationRelativeTo (null);
        loadingScreen.setLocationRelativeTo(null);
        propertiesPage.setLocationRelativeTo (null);
        this.setLocationRelativeTo(null);
        fileChooser = new javax.swing.JFileChooser ();
        
        // Interger filters on formatted textFields
        PlainDocument pd = (PlainDocument) mapWidthFormattedField.getDocument();
        pd.setDocumentFilter(new MyIntFilter());
        PlainDocument pd2 = (PlainDocument) mapHeightFormattedField.getDocument();
        pd2.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd3 = (PlainDocument) roverWidthFormattedField.getDocument();
        pd3.setDocumentFilter(new MyIntFilter());
        PlainDocument pd4 = (PlainDocument) roverHeightFormattedField.getDocument();
        pd4.setDocumentFilter(new MyIntFilter());
        PlainDocument pd5 = (PlainDocument) roverLengthFormattedField.getDocument();
        pd5.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd6 = (PlainDocument) obsticalMarkTextField.getDocument();
        pd6.setDocumentFilter(new LengthFilter (1));
        PlainDocument pd7 = (PlainDocument) emptySpaceMarkTextField.getDocument();
        pd7.setDocumentFilter(new LengthFilter (1));
        
        PlainDocument pd8 = (PlainDocument) positionXFormattedField.getDocument();
        pd8.setDocumentFilter (new MyIntFilter());
        PlainDocument pd9 = (PlainDocument) positionYFormattedField.getDocument();
        pd9.setDocumentFilter (new MyIntFilter());
        
        PlainDocument pd10 = (PlainDocument) headingFormattedField.getDocument();
        pd10.setDocumentFilter(new AngleFilter());
        
        PlainDocument pd11 = (PlainDocument) roverPropertiesWidth.getDocument();
        pd11.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd12 = (PlainDocument) roverPropertiesHeight.getDocument();
        pd12.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd13 = (PlainDocument) roverPropertiesLength.getDocument();
        pd13.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd14 = (PlainDocument) roverPropertiesX.getDocument();
        pd14.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd15 = (PlainDocument) roverPropertiesY.getDocument();
        pd15.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd16 = (PlainDocument) roverPropertiesHeading.getDocument();
        pd16.setDocumentFilter(new AngleFilter());
        
        positionErrorLabel.setForeground(Color.red);
        
        DefaultCaret caret = (DefaultCaret)loadingConsole.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        // Start application
        wizardPage1.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wizardPage1 = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        portComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        baudRateSelector = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        encodeCharsetSelector = new javax.swing.JComboBox<>();
        decodeCharsetSelector = new javax.swing.JComboBox<>();
        start = new javax.swing.JButton();
        errorJLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        wizardPage2 = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        useExistingRadio = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        pathTextField = new javax.swing.JTextField();
        nextButton = new javax.swing.JButton();
        browseButton = new javax.swing.JButton();
        wizardPage2ErrorLabel = new javax.swing.JLabel();
        wizardPage2Back = new javax.swing.JButton();
        fileChooser = new javax.swing.JFileChooser();
        wizardPage3 = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        mapWidthLabel = new javax.swing.JLabel();
        mapWidthFormattedField = new javax.swing.JFormattedTextField();
        mapHeightLabel = new javax.swing.JLabel();
        mapHeightFormattedField = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        useDefaultMarkingsRadio = new javax.swing.JRadioButton();
        obsticalMarkLabel = new javax.swing.JLabel();
        obsticalMarkTextField = new javax.swing.JTextField();
        emptySpaceMarkLabel = new javax.swing.JLabel();
        emptySpaceMarkTextField = new javax.swing.JTextField();
        wizardPage4Next = new javax.swing.JButton();
        wizardPage3Back = new javax.swing.JButton();
        wizardPage4 = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        wizardPage4Title = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        wizardPage4Header2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        roverWidthFormattedField = new javax.swing.JFormattedTextField();
        roverWidthLabel = new javax.swing.JLabel();
        roverLengthLabel = new javax.swing.JLabel();
        roverLengthFormattedField = new javax.swing.JFormattedTextField();
        roverHeightLabel = new javax.swing.JLabel();
        roverHeightFormattedField = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        positionXLabel = new javax.swing.JLabel();
        positionXFormattedField = new javax.swing.JFormattedTextField();
        positionYLabel = new javax.swing.JLabel();
        positionYFormattedField = new javax.swing.JFormattedTextField();
        headingLabel = new javax.swing.JLabel();
        headingFormattedField = new javax.swing.JFormattedTextField();
        jLabel27 = new javax.swing.JLabel();
        wizardPage4Back = new javax.swing.JButton();
        positionErrorLabel = new javax.swing.JLabel();
        about = new javax.swing.JFrame();
        jLabel15 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        loadingScreen = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel12 = new javax.swing.JPanel();
        loadingProgressBar = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        loadingConsole = new javax.swing.JTextArea();
        propertiesPage = new javax.swing.JFrame();
        selectPagePanel = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        preferencePagesList = new javax.swing.JTree();
        propertiesLayers = new javax.swing.JLayeredPane();
        presetPropertiesPanel = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        presetsLayeredPane = new javax.swing.JLayeredPane();
        presetsPage2 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        presetsPreviousPageButton = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        preset4NameField = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        preset4CommandField = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        preset4DescriptionField = new javax.swing.JTextArea();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        preset5NameField = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        preset5CommandField = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        preset5DescriptionField = new javax.swing.JTextArea();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        preset6NameField = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        preset6CommandField = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        preset6DescriptionField = new javax.swing.JTextArea();
        savePresetsButton2 = new javax.swing.JButton();
        presetsPage1 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        preset1NameField = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        preset1CommandField = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        preset1DescriptionField = new javax.swing.JTextArea();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        preset2NameField = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        preset2CommandField = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        preset2DescriptionField = new javax.swing.JTextArea();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        preset3NameField = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        preset3CommandField = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        preset3DescriptionField = new javax.swing.JTextArea();
        presetsNextPageButton = new javax.swing.JButton();
        savePresetsButton1 = new javax.swing.JButton();
        roverPropertiesPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        roverPropertiesWidthLabel = new javax.swing.JLabel();
        roverPropertiesWidth = new javax.swing.JFormattedTextField();
        roverPropertiesLengthLabel = new javax.swing.JLabel();
        roverPropertiesLength = new javax.swing.JFormattedTextField();
        roverPropertiesHeightLabel = new javax.swing.JLabel();
        roverPropertiesHeight = new javax.swing.JFormattedTextField();
        jLabel29 = new javax.swing.JLabel();
        roverPropertiesXLabel = new javax.swing.JLabel();
        roverPropertiesX = new javax.swing.JFormattedTextField();
        roverPropertiesYLabel = new javax.swing.JLabel();
        roverPropertiesY = new javax.swing.JFormattedTextField();
        roverPropertiesHeadingLabel = new javax.swing.JLabel();
        roverPropertiesHeading = new javax.swing.JFormattedTextField();
        jLabel33 = new javax.swing.JLabel();
        roverPropertiesErrorLabel = new javax.swing.JLabel();
        roverPropertiesOk = new javax.swing.JButton();
        roverPropertiesCancel = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        forwardCommandButton = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        leftTurnCommandButton = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        rightTurnCommandButton = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        backwardsCommandButton = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        movementCommandDistance = new javax.swing.JTextField();
        movementCommandAngle = new javax.swing.JTextField();
        movementCommandMM = new javax.swing.JLabel();
        movementCommandDegLabel = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        preset1Button = new javax.swing.JButton();
        preset2Button = new javax.swing.JButton();
        preset4Button = new javax.swing.JButton();
        preset5Button = new javax.swing.JButton();
        preset3Button = new javax.swing.JButton();
        preset6Button = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        sendCommandField = new javax.swing.JTextField();
        sendCommandButton = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        statusDeviceTable = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        statusPosX = new javax.swing.JLabel();
        statusPosY = new javax.swing.JLabel();
        statusPosHeading = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        pathFindAlgorithmSelector = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        pathfindStartXField = new javax.swing.JTextField();
        pathfindStartXLabel = new javax.swing.JLabel();
        pathfindStartYLabel = new javax.swing.JLabel();
        pathfindStartYField = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        pathfindGoalXLabel = new javax.swing.JLabel();
        pathfindGoalXField = new javax.swing.JTextField();
        pathfindGoalYLabel = new javax.swing.JLabel();
        pathfindGoalYField = new javax.swing.JTextField();
        findPathButton = new javax.swing.JButton();
        previewRouteButton = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        pathfindUseRoverPos = new javax.swing.JCheckBox();
        pathfindErrorLabel = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jPanel25 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel82 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        statusBar = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        clock = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        autoSaveCheckBox = new javax.swing.JCheckBoxMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        preferencesItem = new javax.swing.JMenuItem();

        wizardPage1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        wizardPage1.setTitle("Remote Mapper Setup Wizard");
        wizardPage1.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/map.png")).getImage());
        wizardPage1.setResizable(false);
        wizardPage1.setSize(new java.awt.Dimension(383, 200));

        jLabel1.setText("<html><h3>Smart Crew Remote Mapper Setup Wizard</h3></html>");

        jLabel2.setText("<html><h4>Setup a serial connection</h4></html>");

        jLabel3.setText("Port:");

        portComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        portComboBox.setToolTipText("Select the rovers serial port.");
        portComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                portComboBoxPopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel4.setText("Baud rate:");

        baudRateSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "110", "300", "600", "1200", "2400", "4800", "9600", "14400", "19200", "38400", "57600", "115200", "128000", "256000" }));
        baudRateSelector.setSelectedIndex(11);
        baudRateSelector.setToolTipText("Select the bit rate [bit/s] used to communicate.");

        jLabel5.setText("Encode charset:");

        jLabel6.setText("Decode charset:");

        encodeCharsetSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16" }));
        encodeCharsetSelector.setToolTipText("Select the character set used to decode transmissions.");

        decodeCharsetSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16" }));
        decodeCharsetSelector.setToolTipText("Select the character set used to encode data to be sent.");

        start.setText("<html><h4>Next</h4></html>");
        start.setToolTipText("");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(errorJLabel)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel3))
                                            .addGap(10, 10, 10)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(portComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(baudRateSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(36, 36, 36)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(decodeCharsetSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(encodeCharsetSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(portComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(encodeCharsetSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(baudRateSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(decodeCharsetSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorJLabel))
                .addGap(102, 102, 102))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout wizardPage1Layout = new javax.swing.GroupLayout(wizardPage1.getContentPane());
        wizardPage1.getContentPane().setLayout(wizardPage1Layout);
        wizardPage1Layout.setHorizontalGroup(
            wizardPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizardPage1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        wizardPage1Layout.setVerticalGroup(
            wizardPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        wizardPage2.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        wizardPage2.setTitle("Remote Mapper Setup Wizard");
        wizardPage2.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/map.png")).getImage());
        wizardPage2.setResizable(false);
        wizardPage2.setSize(new java.awt.Dimension(383, 200));

        jLabel7.setText("<html><h3>Smart Crew Remote Mapper Setup Wizard</h3></html>");

        jLabel8.setText("<html><h4>Setup the workspace</h4></html>");

        jLabel9.setText("Use existing workspace:");

        useExistingRadio.setText("Yes");
        useExistingRadio.setToolTipText("Select if you want to use an existing workspace.");

        jLabel10.setText("Workspace path:");

        nextButton.setText("<html><h4>Next</h4></html>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        browseButton.setText("Browse");
        browseButton.setToolTipText("");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        wizardPage2Back.setText("<html><h4>Back</h4></html>");
        wizardPage2Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wizardPage2BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(wizardPage2ErrorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(wizardPage2Back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(useExistingRadio))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(browseButton))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(useExistingRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wizardPage2Back, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(wizardPage2ErrorLabel))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout wizardPage2Layout = new javax.swing.GroupLayout(wizardPage2.getContentPane());
        wizardPage2.getContentPane().setLayout(wizardPage2Layout);
        wizardPage2Layout.setHorizontalGroup(
            wizardPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        wizardPage2Layout.setVerticalGroup(
            wizardPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        wizardPage3.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        wizardPage3.setTitle("Remote Mapper Setup Wizard");
        wizardPage3.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/map.png")).getImage());
        wizardPage3.setResizable(false);
        wizardPage3.setSize(new java.awt.Dimension(383, 200));

        jLabel12.setText("<html><h3>Smart Crew Remote Mapper Setup Wizard</h3></html>");

        jLabel13.setText("<html><h4>Setup the map</h4></html>");

        jLabel14.setText("Map size:");

        mapWidthLabel.setText("width");

        mapWidthFormattedField.setText("100");
        mapWidthFormattedField.setToolTipText("Select the maps width.");

        mapHeightLabel.setText("height");

        mapHeightFormattedField.setText("100");
        mapHeightFormattedField.setToolTipText("Select the maps height.");

        jLabel17.setText("Markings:");

        jLabel18.setText("use default:");

        useDefaultMarkingsRadio.setSelected(true);
        useDefaultMarkingsRadio.setText("yes");
        useDefaultMarkingsRadio.setToolTipText("Select if you want to use the default map markings.");
        useDefaultMarkingsRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useDefaultMarkingsRadioActionPerformed(evt);
            }
        });

        obsticalMarkLabel.setText("Obstical:");
        obsticalMarkLabel.setEnabled(false);

        obsticalMarkTextField.setColumns(1);
        obsticalMarkTextField.setText("1");
        obsticalMarkTextField.setToolTipText("Select the charactar to be used to mark an obstical on the map.");
        obsticalMarkTextField.setEnabled(false);

        emptySpaceMarkLabel.setText("Empty space:");
        emptySpaceMarkLabel.setEnabled(false);

        emptySpaceMarkTextField.setColumns(1);
        emptySpaceMarkTextField.setText("0");
        emptySpaceMarkTextField.setToolTipText("Select the character to be used to mark empty space on the map.");
        emptySpaceMarkTextField.setEnabled(false);

        wizardPage4Next.setText("<html><h4>Next</h4></html>");
        wizardPage4Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wizardPage4NextActionPerformed(evt);
            }
        });

        wizardPage3Back.setText("<html><h4>Back</h4></html>");
        wizardPage3Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wizardPage3BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mapWidthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mapWidthFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mapHeightLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mapHeightFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(obsticalMarkLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(obsticalMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(emptySpaceMarkLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(emptySpaceMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(useDefaultMarkingsRadio)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wizardPage4Next, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wizardPage3Back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(mapWidthLabel)
                            .addComponent(mapWidthFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mapHeightLabel)
                            .addComponent(mapHeightFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(useDefaultMarkingsRadio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(obsticalMarkLabel)
                            .addComponent(obsticalMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emptySpaceMarkLabel)
                            .addComponent(emptySpaceMarkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(wizardPage3Back, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(wizardPage4Next, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout wizardPage3Layout = new javax.swing.GroupLayout(wizardPage3.getContentPane());
        wizardPage3.getContentPane().setLayout(wizardPage3Layout);
        wizardPage3Layout.setHorizontalGroup(
            wizardPage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        wizardPage3Layout.setVerticalGroup(
            wizardPage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wizardPage3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        wizardPage4.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        wizardPage4.setTitle("Remote Mapper Setup Wizard");
        wizardPage4.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/map.png")).getImage());
        wizardPage4.setResizable(false);
        wizardPage4.setSize(new java.awt.Dimension(383, 200));

        wizardPage4Title.setText("<html><h3>Smart Crew Remote Mapper Setup Wizard</h3></html>");

        wizardPage4Header2.setText("<html><h4>Setup the rover</h4></html>");

        jLabel11.setText("Dimensions (mm):");

        roverWidthFormattedField.setText("270");

        roverWidthLabel.setText("width");

        roverLengthLabel.setText("length");

        roverLengthFormattedField.setText("370");

        roverHeightLabel.setText("height");

        roverHeightFormattedField.setText("200");

        jButton2.setText("<html><h4>Finish</h4></html>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel23.setText("Position:");

        positionXLabel.setText("x");

        positionXFormattedField.setText("0");

        positionYLabel.setText("y");

        positionYFormattedField.setText("0");

        headingLabel.setText("Heading:");

        headingFormattedField.setText("0");

        jLabel27.setText("°");

        wizardPage4Back.setText("<html><h4>Back</h4></html>");
        wizardPage4Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wizardPage4BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(positionErrorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wizardPage4Back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wizardPage4Header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(4, 4, 4)
                                .addComponent(roverWidthLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverWidthFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverLengthLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverLengthFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverHeightLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverHeightFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(positionXLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(positionXFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(positionYLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(positionYFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(headingLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(headingFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(wizardPage4Header2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(roverWidthFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roverWidthLabel)
                    .addComponent(roverLengthLabel)
                    .addComponent(roverLengthFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roverHeightLabel)
                    .addComponent(roverHeightFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(positionXLabel)
                    .addComponent(positionXFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(positionYLabel)
                    .addComponent(positionYFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(headingLabel)
                    .addComponent(headingFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wizardPage4Back, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(positionErrorLabel))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(wizardPage4Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wizardPage4Title, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout wizardPage4Layout = new javax.swing.GroupLayout(wizardPage4.getContentPane());
        wizardPage4.getContentPane().setLayout(wizardPage4Layout);
        wizardPage4Layout.setHorizontalGroup(
            wizardPage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        wizardPage4Layout.setVerticalGroup(
            wizardPage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        about.setTitle("About Remote Mapper");
        about.setAlwaysOnTop(true);
        about.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/info.png")).getImage());
        about.setResizable(false);
        about.setSize(new java.awt.Dimension(439, 300));
        about.setType(java.awt.Window.Type.POPUP);

        jLabel15.setText("<html><h2>Remote Mapper</h2></html");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel21.setText("<html><p>Robert Hutter<br />Version 1206<br />No rights reserved.</p></html>");

        jLabel22.setText("<html><div>Icons made by <a href=\"https://www.flaticon.com/authors/roundicons\" title=\"Roundicons\">Roundicons</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div><br /><div>Icons made by <a href=\"https://www.flaticon.com/authors/smashicons\" title=\"Smashicons\">Smashicons</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div></html>");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout aboutLayout = new javax.swing.GroupLayout(about.getContentPane());
        about.getContentPane().setLayout(aboutLayout);
        aboutLayout.setHorizontalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutLayout.createSequentialGroup()
                .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aboutLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator5))
                    .addGroup(aboutLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(aboutLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aboutLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aboutLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        aboutLayout.setVerticalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        loadingScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        loadingScreen.setTitle("Remote Mapper Wizard");
        loadingScreen.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/map.png")).getImage());
        loadingScreen.setResizable(false);
        loadingScreen.setSize(new java.awt.Dimension(383, 200));

        jLabel24.setText("<html><h3>Prepairing your workspace</h3></html>");

        loadingConsole.setEditable(false);
        loadingConsole.setColumns(20);
        loadingConsole.setRows(5);
        jScrollPane1.setViewportView(loadingConsole);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(loadingProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadingProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator6))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 96, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout loadingScreenLayout = new javax.swing.GroupLayout(loadingScreen.getContentPane());
        loadingScreen.getContentPane().setLayout(loadingScreenLayout);
        loadingScreenLayout.setHorizontalGroup(
            loadingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        loadingScreenLayout.setVerticalGroup(
            loadingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        propertiesPage.setTitle("Properties");
        propertiesPage.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/map.png")).getImage());
        propertiesPage.setResizable(false);
        propertiesPage.setSize(new java.awt.Dimension(788, 524));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel30.setText("<html><h4>Properties</h4></html>");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Properties");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Rover");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Commands");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Presets");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        preferencePagesList.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        preferencePagesList.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                preferencePagesListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(preferencePagesList);

        javax.swing.GroupLayout selectPagePanelLayout = new javax.swing.GroupLayout(selectPagePanel);
        selectPagePanel.setLayout(selectPagePanelLayout);
        selectPagePanelLayout.setHorizontalGroup(
            selectPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectPagePanelLayout.createSequentialGroup()
                .addGroup(selectPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectPagePanelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE))
                    .addGroup(selectPagePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        selectPagePanelLayout.setVerticalGroup(
            selectPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectPagePanelLayout.createSequentialGroup()
                .addGroup(selectPagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectPagePanelLayout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                    .addGroup(selectPagePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator7)))
                .addContainerGap())
        );

        jLabel37.setText("<html><h3>Preset commands</h3></html>");

        jLabel55.setText("Preset 4");

        presetsPreviousPageButton.setText("Previous Page");
        presetsPreviousPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presetsPreviousPageButtonActionPerformed(evt);
            }
        });

        jLabel56.setText("Name:");

        preset4NameField.setText("Preset 4");
        preset4NameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                preset4NameFieldFocusLost(evt);
            }
        });

        jLabel57.setText("Command:");

        jLabel58.setText("Description:");

        preset4DescriptionField.setColumns(20);
        preset4DescriptionField.setRows(5);
        jScrollPane7.setViewportView(preset4DescriptionField);

        jLabel59.setText("Preset 5");

        jLabel60.setText("Name:");

        preset5NameField.setText("Preset 5");
        preset5NameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                preset5NameFieldFocusLost(evt);
            }
        });

        jLabel61.setText("Command:");

        jLabel62.setText("Description:");

        preset5DescriptionField.setColumns(20);
        preset5DescriptionField.setRows(5);
        jScrollPane8.setViewportView(preset5DescriptionField);

        jLabel63.setText("Preset 6");

        jLabel64.setText("Name:");

        preset6NameField.setText("Preset 6");
        preset6NameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                preset6NameFieldFocusLost(evt);
            }
        });

        jLabel65.setText("Command:");

        jLabel66.setText("Description:");

        preset6DescriptionField.setColumns(20);
        preset6DescriptionField.setRows(5);
        jScrollPane9.setViewportView(preset6DescriptionField);

        savePresetsButton2.setText("Save");
        savePresetsButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePresetsButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout presetsPage2Layout = new javax.swing.GroupLayout(presetsPage2);
        presetsPage2.setLayout(presetsPage2Layout);
        presetsPage2Layout.setHorizontalGroup(
            presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetsPage2Layout.createSequentialGroup()
                .addGroup(presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addComponent(jLabel59)
                    .addComponent(jLabel63))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(presetsPage2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(presetsPage2Layout.createSequentialGroup()
                        .addGroup(presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(presetsPage2Layout.createSequentialGroup()
                                    .addComponent(jLabel56)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(preset4NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel57)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(preset4CommandField, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                                .addComponent(jLabel58)
                                .addGroup(presetsPage2Layout.createSequentialGroup()
                                    .addComponent(jLabel60)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(preset5NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel61)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(preset5CommandField))
                                .addComponent(jLabel62)
                                .addGroup(presetsPage2Layout.createSequentialGroup()
                                    .addComponent(jLabel64)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(preset6NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel65)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(preset6CommandField))
                                .addComponent(jLabel66)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(87, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, presetsPage2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(presetsPreviousPageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savePresetsButton2))))
        );
        presetsPage2Layout.setVerticalGroup(
            presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetsPage2Layout.createSequentialGroup()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(preset4NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(preset4CommandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(preset5NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61)
                    .addComponent(preset5CommandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(preset6NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(preset6CommandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(presetsPage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presetsPreviousPageButton)
                    .addComponent(savePresetsButton2)))
        );

        jLabel42.setText("Preset 1");

        jLabel43.setText("Name:");

        preset1NameField.setText("Preset 1");
        preset1NameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                preset1NameFieldFocusLost(evt);
            }
        });

        jLabel44.setText("Command:");

        jLabel46.setText("Description:");

        preset1DescriptionField.setColumns(20);
        preset1DescriptionField.setRows(5);
        jScrollPane4.setViewportView(preset1DescriptionField);

        jLabel47.setText("Preset 2");

        jLabel48.setText("Name:");

        preset2NameField.setText("Preset 2");
        preset2NameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                preset2NameFieldFocusLost(evt);
            }
        });

        jLabel49.setText("Command:");

        jLabel50.setText("Description:");

        preset2DescriptionField.setColumns(20);
        preset2DescriptionField.setRows(5);
        jScrollPane5.setViewportView(preset2DescriptionField);

        jLabel51.setText("Preset 3");

        jLabel52.setText("Name:");

        preset3NameField.setText("Preset 3");
        preset3NameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                preset3NameFieldFocusLost(evt);
            }
        });

        jLabel53.setText("Command:");

        jLabel54.setText("Description:");

        preset3DescriptionField.setColumns(20);
        preset3DescriptionField.setRows(5);
        jScrollPane6.setViewportView(preset3DescriptionField);

        presetsNextPageButton.setText("Next page");
        presetsNextPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presetsNextPageButtonActionPerformed(evt);
            }
        });

        savePresetsButton1.setText("Save");
        savePresetsButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePresetsButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout presetsPage1Layout = new javax.swing.GroupLayout(presetsPage1);
        presetsPage1.setLayout(presetsPage1Layout);
        presetsPage1Layout.setHorizontalGroup(
            presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetsPage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(presetsPage1Layout.createSequentialGroup()
                        .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, presetsPage1Layout.createSequentialGroup()
                                    .addComponent(jLabel42)
                                    .addGap(280, 280, 280))
                                .addGroup(presetsPage1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel46)
                                            .addGroup(presetsPage1Layout.createSequentialGroup()
                                                .addComponent(jLabel43)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(preset1NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel44)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(preset1CommandField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, presetsPage1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(presetsPage1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, presetsPage1Layout.createSequentialGroup()
                                            .addComponent(jLabel48)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(preset2NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel49)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(preset2CommandField))
                                        .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, presetsPage1Layout.createSequentialGroup()
                                            .addComponent(jLabel52)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(preset3NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel53)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(preset3CommandField))))))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, presetsPage1Layout.createSequentialGroup()
                        .addComponent(presetsNextPageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savePresetsButton1))))
        );
        presetsPage1Layout.setVerticalGroup(
            presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetsPage1Layout.createSequentialGroup()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(preset1NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(preset1CommandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(preset2NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49)
                    .addComponent(preset2CommandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(preset3NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(preset3CommandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(presetsPage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presetsNextPageButton)
                    .addComponent(savePresetsButton1)))
        );

        presetsLayeredPane.setLayer(presetsPage2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        presetsLayeredPane.setLayer(presetsPage1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout presetsLayeredPaneLayout = new javax.swing.GroupLayout(presetsLayeredPane);
        presetsLayeredPane.setLayout(presetsLayeredPaneLayout);
        presetsLayeredPaneLayout.setHorizontalGroup(
            presetsLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(presetsPage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(presetsLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, presetsLayeredPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(presetsPage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        presetsLayeredPaneLayout.setVerticalGroup(
            presetsLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(presetsPage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(presetsLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(presetsPage2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout presetPropertiesPanelLayout = new javax.swing.GroupLayout(presetPropertiesPanel);
        presetPropertiesPanel.setLayout(presetPropertiesPanelLayout);
        presetPropertiesPanelLayout.setHorizontalGroup(
            presetPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetPropertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(presetPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(presetsLayeredPane)
                    .addGroup(presetPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(jSeparator12)
                        .addContainerGap())
                    .addGroup(presetPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        presetPropertiesPanelLayout.setVerticalGroup(
            presetPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetPropertiesPanelLayout.createSequentialGroup()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(presetsLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel19.setText("<html><h3>Rover Properties</h3></html>");

        jLabel20.setText("<html><h4>Size</h4></html>");

        roverPropertiesWidthLabel.setText("Width:");

        roverPropertiesLengthLabel.setText("length:");

        roverPropertiesHeightLabel.setText("height:");

        jLabel29.setText("<html><h4>Position</h4></html>");

        roverPropertiesXLabel.setText("x:");

        roverPropertiesYLabel.setText("y:");

        roverPropertiesHeadingLabel.setText("heading:");

        jLabel33.setText("°");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(roverPropertiesWidthLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverPropertiesWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverPropertiesLengthLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverPropertiesLength, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverPropertiesHeightLabel)
                                .addGap(7, 7, 7)
                                .addComponent(roverPropertiesHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(158, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(roverPropertiesXLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverPropertiesX, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverPropertiesYLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverPropertiesY, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverPropertiesHeadingLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roverPropertiesHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33))
                            .addComponent(roverPropertiesErrorLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roverPropertiesWidthLabel)
                    .addComponent(roverPropertiesWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roverPropertiesLengthLabel)
                    .addComponent(roverPropertiesLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roverPropertiesHeightLabel)
                    .addComponent(roverPropertiesHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roverPropertiesXLabel)
                    .addComponent(roverPropertiesX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roverPropertiesYLabel)
                    .addComponent(roverPropertiesY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roverPropertiesHeadingLabel)
                    .addComponent(roverPropertiesHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addComponent(roverPropertiesErrorLabel)
                .addGap(34, 34, 34))
        );

        roverPropertiesOk.setText("OK");
        roverPropertiesOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roverPropertiesOkActionPerformed(evt);
            }
        });

        roverPropertiesCancel.setText("Cancel");
        roverPropertiesCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roverPropertiesCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roverPropertiesPanelLayout = new javax.swing.GroupLayout(roverPropertiesPanel);
        roverPropertiesPanel.setLayout(roverPropertiesPanelLayout);
        roverPropertiesPanelLayout.setHorizontalGroup(
            roverPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roverPropertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roverPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator10)
                    .addGroup(roverPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(roverPropertiesCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roverPropertiesOk))
                    .addGroup(roverPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roverPropertiesPanelLayout.setVerticalGroup(
            roverPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roverPropertiesPanelLayout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(roverPropertiesPanelLayout.createSequentialGroup()
                .addContainerGap(479, Short.MAX_VALUE)
                .addGroup(roverPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roverPropertiesOk)
                    .addComponent(roverPropertiesCancel)))
        );

        propertiesLayers.setLayer(presetPropertiesPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        propertiesLayers.setLayer(roverPropertiesPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout propertiesLayersLayout = new javax.swing.GroupLayout(propertiesLayers);
        propertiesLayers.setLayout(propertiesLayersLayout);
        propertiesLayersLayout.setHorizontalGroup(
            propertiesLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertiesLayersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roverPropertiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(propertiesLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, propertiesLayersLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(presetPropertiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        propertiesLayersLayout.setVerticalGroup(
            propertiesLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roverPropertiesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(propertiesLayersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(presetPropertiesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout propertiesPageLayout = new javax.swing.GroupLayout(propertiesPage.getContentPane());
        propertiesPage.getContentPane().setLayout(propertiesPageLayout);
        propertiesPageLayout.setHorizontalGroup(
            propertiesPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, propertiesPageLayout.createSequentialGroup()
                .addComponent(selectPagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(propertiesLayers)
                .addContainerGap())
        );
        propertiesPageLayout.setVerticalGroup(
            propertiesPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectPagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(propertiesPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(propertiesLayers)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Remote Mapper");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/map.png")).getImage());
        setSize(new java.awt.Dimension(830, 550));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel25.setText("<html><h4>Send command</h4></html>");

        jPanel14.setLayout(new java.awt.GridLayout(3, 3));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel17);

        forwardCommandButton.setText("^");
        forwardCommandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardCommandButtonActionPerformed(evt);
            }
        });
        jPanel14.add(forwardCommandButton);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel21);

        leftTurnCommandButton.setText("<");
        leftTurnCommandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftTurnCommandButtonActionPerformed(evt);
            }
        });
        jPanel14.add(leftTurnCommandButton);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel18);

        rightTurnCommandButton.setText(">");
        rightTurnCommandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightTurnCommandButtonActionPerformed(evt);
            }
        });
        jPanel14.add(rightTurnCommandButton);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel19);

        backwardsCommandButton.setText("ˇ");
        backwardsCommandButton.setPreferredSize(new java.awt.Dimension(50, 50));
        backwardsCommandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backwardsCommandButtonActionPerformed(evt);
            }
        });
        jPanel14.add(backwardsCommandButton);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel20);

        jLabel26.setText("Distance:");

        jLabel28.setText("Angle:");

        movementCommandDistance.setText("500");

        movementCommandAngle.setText("90");

        movementCommandMM.setText("mm");

        movementCommandDegLabel.setText("°");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movementCommandDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movementCommandMM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movementCommandAngle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movementCommandDegLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28)
                    .addComponent(movementCommandDistance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movementCommandAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movementCommandMM)
                    .addComponent(movementCommandDegLabel))
                .addGap(12, 12, 12)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Movement", jPanel15);

        jLabel34.setText("Presets");

        preset1Button.setText("Preset 1");
        preset1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preset1ButtonActionPerformed(evt);
            }
        });

        preset2Button.setText("Preset 2");
        preset2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preset2ButtonActionPerformed(evt);
            }
        });

        preset4Button.setText("Preset 4");
        preset4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preset4ButtonActionPerformed(evt);
            }
        });

        preset5Button.setText("Preset 5");
        preset5Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preset5ButtonActionPerformed(evt);
            }
        });

        preset3Button.setText("Preset 3");
        preset3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preset3ButtonActionPerformed(evt);
            }
        });

        preset6Button.setText("Preset 6");
        preset6Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preset6ButtonActionPerformed(evt);
            }
        });

        jLabel35.setText("Custom command:");

        sendCommandButton.setText("Send");
        sendCommandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendCommandButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(sendCommandField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendCommandButton))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(preset2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(preset1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel34)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(preset4Button, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(preset5Button, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(preset3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(preset6Button, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel35))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preset1Button)
                    .addComponent(preset4Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preset2Button)
                    .addComponent(preset5Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preset3Button)
                    .addComponent(preset6Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendCommandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendCommandButton))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Custom", jPanel16);

        jLabel32.setText("<html><h4>Status</h4></html>");

        jLabel36.setText("<html><b>Position</b></html>");

        jLabel38.setText("x");

        jLabel39.setText("y");

        jLabel40.setText("<html><b>Devices</b></html>");

        statusDeviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Device name", "Status", "Last checked"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(statusDeviceTable);
        if (statusDeviceTable.getColumnModel().getColumnCount() > 0) {
            statusDeviceTable.getColumnModel().getColumn(0).setResizable(false);
            statusDeviceTable.getColumnModel().getColumn(1).setResizable(false);
            statusDeviceTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel41.setText("heading");

        statusPosX.setText("XXX");

        statusPosY.setText("YYY");

        statusPosHeading.setText("HHH.H");

        jLabel45.setText("°");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusPosX))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusPosY))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusPosHeading)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel45)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(statusPosX))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(statusPosY))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(statusPosHeading)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel31.setText("<html><h4>Edit map</h4></html");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Points", jPanel22);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Shape", jPanel23);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Custom", jPanel24);

        jLabel67.setText("<html><h4>Pathfind</h4></html>");

        jLabel68.setText("Algorithm:");

        pathFindAlgorithmSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A*" }));

        jLabel69.setText("From:");

        pathfindStartXField.setText("0");

        pathfindStartXLabel.setText("x");

        pathfindStartYLabel.setText("y");

        pathfindStartYField.setText("0");

        jLabel72.setText("To:");

        pathfindGoalXLabel.setText("x");

        pathfindGoalXField.setText("0");

        pathfindGoalYLabel.setText("y");

        pathfindGoalYField.setText("0");

        findPathButton.setText("Find path");
        findPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findPathButtonActionPerformed(evt);
            }
        });

        previewRouteButton.setText("Preview route");

        jLabel75.setText("Route details");

        jLabel76.setText("Distance:");

        jLabel77.setText("XXX.XX");

        jLabel78.setText("cm");

        pathfindUseRoverPos.setText("Rover pos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel75)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel76)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel77)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel78)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pathFindAlgorithmSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(findPathButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(previewRouteButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel72)
                                        .addGap(18, 18, 18)
                                        .addComponent(pathfindGoalXLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pathfindGoalXField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel69)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pathfindStartXLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pathfindStartXField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(pathfindStartYLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pathfindStartYField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(pathfindGoalYLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pathfindGoalYField)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pathfindUseRoverPos))
                            .addComponent(jTabbedPane2))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pathfindErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(pathFindAlgorithmSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(pathfindStartXField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pathfindStartXLabel)
                    .addComponent(pathfindStartYLabel)
                    .addComponent(pathfindStartYField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pathfindUseRoverPos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(pathfindGoalXLabel)
                    .addComponent(pathfindGoalXField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pathfindGoalYLabel)
                    .addComponent(pathfindGoalYField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previewRouteButton)
                    .addComponent(findPathButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathfindErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(jLabel77)
                    .addComponent(jLabel78))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel79.setText("<html><h3>Preview map</h3></html>");

        jLabel80.setText("<html><h4>Full scale</h4></html>");

        jLabel81.setText("<html><h4>Simplefied</h4></html>");

        jTextPane1.setEditable(false);
        jScrollPane10.setViewportView(jTextPane1);

        jTextPane2.setEditable(false);
        jScrollPane11.setViewportView(jTextPane2);

        jLabel82.setText("<html><h4>Details</h4></html>");

        jLabel84.setText("<html><h4>Details</h4></html>");

        jLabel83.setText("Size:");

        jLabel85.setText("XX x YY cm");

        jLabel86.setText("<html><b>Markings</b></html>");

        jLabel87.setText("Obstical:");

        jLabel88.setText("Clear:");

        jLabel89.setText("X");

        jLabel90.setText("X");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel85))
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel88)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel87)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel89)))))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(jLabel85))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(jLabel89))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(jLabel90))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel91.setText("Size:");

        jLabel92.setText("XX x YY cm");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel92)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(jLabel92))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE))
                                .addComponent(jScrollPane11))
                            .addGap(5, 5, 5)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane11))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator11)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator13)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 24, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        statusBar.setMaximumSize(new java.awt.Dimension(32767, 22));
        statusBar.setMinimumSize(new java.awt.Dimension(100, 22));
        statusBar.setPreferredSize(new java.awt.Dimension(909, 22));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        clock.setText("00:00.00");

        javax.swing.GroupLayout statusBarLayout = new javax.swing.GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(clock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        statusBarLayout.setVerticalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusBarLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusBarLayout.createSequentialGroup()
                        .addComponent(clock)
                        .addGap(4, 4, 4))
                    .addGroup(statusBarLayout.createSequentialGroup()
                        .addComponent(jSeparator9)
                        .addGap(3, 3, 3))))
        );

        jMenu1.setText("File");

        autoSaveCheckBox.setSelected(true);
        autoSaveCheckBox.setText("Autosave");
        jMenu1.add(autoSaveCheckBox);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setText("Save As...");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveAsMenuItem);

        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/info.png"))); // NOI18N
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(aboutMenuItem);

        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exit.png"))); // NOI18N
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        preferencesItem.setText("Preferences");
        preferencesItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferencesItemActionPerformed(evt);
            }
        });
        jMenu2.add(preferencesItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator8)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:
        try {
            if (!portComboBox.getSelectedItem().equals("Select"))
            {
                port.open((String) portComboBox.getSelectedItem(), Integer.parseInt((String) baudRateSelector.getSelectedItem()), (String) decodeCharsetSelector.getSelectedItem());
                errorJLabel.setText("");
                wizardPage1.setVisible(false);
                wizardPage2.setVisible(true);
            } else
            {
                errorJLabel.setForeground(Color.red);
                errorJLabel.setText ("Please select a port.");
            }
        } catch (SerialException ex) {
            errorJLabel.setForeground(Color.red);
            errorJLabel.setText("Selected port busy. Please try again.");
            Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_startActionPerformed

    private void portComboBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_portComboBoxPopupMenuWillBecomeVisible
        SerialPort[] ports = SerialHandler.getCommPorts();
        portComboBox.removeAllItems();
        for (SerialPort port1 : ports) {
            portComboBox.addItem(port1.getSystemPortName());
        }
        
        if (portComboBox.getItemCount() < 1)
            portComboBox.addItem("Select");
    }//GEN-LAST:event_portComboBoxPopupMenuWillBecomeVisible

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showDialog(wizardPage2, "Select workspace") == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            pathTextField.setText(fileChooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        // Make sure that the workspace is valid
        pathTextField.setEditable (false);
        File ws = new File (pathTextField.getText());
        if (ws.isDirectory())
        {
            if (existingWorkspaceValid (ws))
            {
                if (useExistingRadio.isSelected())
                {
                    workspace = ws;
                    wizardPage2.setVisible (false);
                    wizardPage4.setVisible(true);
                    pathTextField.setEditable(true);
                }
                else
                {
                    if (0 == JOptionPane.showConfirmDialog(null, "Selected workspace contains valid data. Would you like to overwrite it?", "Overwrite workspace?", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION))
                    {
                        workspace = ws;
                        wizardPage2.setVisible(false);
                        wizardPage3.setVisible(true);
                    }
                    
                    pathTextField.setEditable (true);
                  }
            }
            else
            {
                if (useExistingRadio.isSelected())
                {
                    wizardPage2ErrorLabel.setForeground(Color.red);
                    wizardPage2ErrorLabel.setText("Selected workspace invalid.");
                    pathTextField.setEditable(true);
                }
                else
                {
                    workspace = ws;
                    wizardPage2.setVisible(false);
                    wizardPage3.setVisible(true);
                    pathTextField.setEditable(true);
                }
            }
            
            
        }
        else
        {
            wizardPage2ErrorLabel.setForeground(Color.red);
            wizardPage2ErrorLabel.setText("Please select a workspace.");
            pathTextField.setEditable(true);
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void useDefaultMarkingsRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useDefaultMarkingsRadioActionPerformed
        // TODO add your handling code here:
        if (useDefaultMarkingsRadio.isSelected())
        {
            obsticalMarkLabel.setEnabled(false);
            obsticalMarkLabel.setForeground(Color.black);
            obsticalMarkTextField.setText("1");
            obsticalMarkTextField.setEnabled(false);
            
            emptySpaceMarkLabel.setEnabled(false);
            emptySpaceMarkLabel.setForeground(Color.black);
            emptySpaceMarkTextField.setText("0");
            emptySpaceMarkTextField.setEnabled(false);
        }
        else
        {
            obsticalMarkLabel.setEnabled(true);
            obsticalMarkTextField.setEnabled(true);
            
            emptySpaceMarkLabel.setEnabled(true);
            emptySpaceMarkTextField.setEnabled(true);
        }
        
    }//GEN-LAST:event_useDefaultMarkingsRadioActionPerformed

    private void wizardPage4NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wizardPage4NextActionPerformed
        // TODO add your handling code here:
        // Test values
        boolean ok = true;
        
        if (mapWidthFormattedField.getText().isEmpty())
        {
            ok = false;
            mapWidthLabel.setForeground(Color.red);
        }
        else
            mapWidthLabel.setForeground(Color.black);
        
        if (mapHeightFormattedField.getText().isEmpty())
        {
            ok = false;
            mapHeightLabel.setForeground(Color.red);
        }
        else
            mapHeightLabel.setForeground(Color.black);
        
        if (obsticalMarkTextField.getText().isEmpty())
        {
            ok = false;
            obsticalMarkLabel.setForeground(Color.red);
        }
        else
            obsticalMarkLabel.setForeground(Color.black);
        
        if (emptySpaceMarkTextField.getText().isEmpty())
        {
            ok = false;
            emptySpaceMarkLabel.setForeground(Color.red);
        }
        else
            emptySpaceMarkLabel.setForeground(Color.black);
        
        if (ok)
        {
            wizardPage3.setVisible(false);
            wizardPage4.setVisible(true);
        }
    }//GEN-LAST:event_wizardPage4NextActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Make sure all data is valid
        boolean ok = true;
        
        if (roverHeightFormattedField.getText().isEmpty())
        {
            ok = false;
            roverHeightLabel.setForeground(Color.red);
        }
        else
            roverHeightLabel.setForeground(Color.black);
        
        if (roverLengthFormattedField.getText().isEmpty())
        {
            ok = false;
            roverLengthLabel.setForeground(Color.red);
        }
        else
            roverLengthLabel.setForeground(Color.black);
        
        if (roverWidthFormattedField.getText().isEmpty())
        {
            ok = false;
            roverWidthLabel.setForeground(Color.red);
        }
        else
            roverWidthLabel.setForeground(Color.black);
        
        if (positionXFormattedField.getText().isEmpty())
        {
            ok = false;
            positionXLabel.setForeground(Color.red);
        }
        else
            positionXLabel.setForeground (Color.black);
        
        if (positionYFormattedField.getText().isEmpty())
        {
            ok = false;
            positionYLabel.setForeground(Color.red);
        }
        else
            positionYLabel.setForeground (Color.black);
        
        if (headingFormattedField.getText().isEmpty())
        {
            ok = false;
            headingLabel.setForeground(Color.red);
        }
        else
            headingLabel.setForeground(Color.black);
        
        // Check to make sure position is valid:
        if (!useExistingRadio.isSelected())
        {
            if (Integer.parseInt(positionXFormattedField.getText()) > Integer.parseInt (mapWidthFormattedField.getText()))
            {
                positionErrorLabel.setText("Invalid position. X too big.");
                ok = false;
            }
            else if (Integer.parseInt(positionYFormattedField.getText()) > Integer.parseInt(mapHeightFormattedField.getText()))
            {
                positionErrorLabel.setText("Invalid position. Y too big.");
                ok = false;
            }
            else
                positionErrorLabel.setText("");
        }
        
        
        // Only continue if all data is valid
        if (!ok)
            return;
        
        wizardPage4.setVisible(false);
        loadingScreen.setVisible(true);

        // load workspace
        // If using an existing workspace, parse map
        if (useExistingRadio.isSelected())
        {
            loadingConsole.append("Loading map...\n");
            mapFile = new File (pathTextField.getText() + File.separator + ConfigFiles.MAP.getName());
            presetFile = new File (pathTextField.getText() + File.separator + ConfigFiles.CMD_PRESETS.getName());

            loadingProgressBar.setMaximum((int) mapFile.length());

            presets = new CommandPreset[6];

            LoadWorkspace lm = new LoadWorkspace(mapFile, presets, presetFile);

            lm.execute();

            // Create new rover
            rover = new Rover (Integer.parseInt(positionXFormattedField.getText()), Integer.parseInt(positionYFormattedField.getText()), Float.parseFloat(headingFormattedField.getText()), Integer.parseInt(roverWidthFormattedField.getText()), Integer.parseInt(roverLengthFormattedField.getText()));
        }
        else
        {
            // Create new map
            loadingConsole.append("Creating new map...\n");
            map = new Map (Integer.parseInt(mapWidthFormattedField.getText()), Integer.parseInt(mapHeightFormattedField.getText()), obsticalMarkTextField.getText().charAt(0), emptySpaceMarkTextField.getText().charAt(0));
            loadingConsole.append ("Done...\n");

            // Instancise files
            mapFile = new File (workspace.getPath() + File.separator + ConfigFiles.MAP.getName());
            presetFile = new File (workspace.getPath() + File.separator + ConfigFiles.CMD_PRESETS.getName());

            loadingConsole.append ("Saving workspace... [1/2]\n");

            // Setup new command presets
            presets = new CommandPreset[6];

            for (int i = 0; i < presets.length; i++)
            {
                presets[i] = new CommandPreset ("Preset "+(i+1), i);
            }

            // Save workspace
            SaveWorkspace sm = new SaveWorkspace (map, mapFile, presets, presetFile, loadingProgressBar, loadingConsole);
            sm.execute();

            // Create new rover
            rover = new Rover (Integer.parseInt(positionXFormattedField.getText()), Integer.parseInt(positionYFormattedField.getText()), Float.parseFloat(headingFormattedField.getText()), Integer.parseInt(roverWidthFormattedField.getText()), Integer.parseInt(roverLengthFormattedField.getText()));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        about.setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        about.setLocationRelativeTo (null);
        about.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO: Save workspace before closing it!
        
        System.exit (0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void wizardPage2BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wizardPage2BackActionPerformed
        port.close();
        wizardPage2.setVisible(false);
        wizardPage1.setVisible (true);
    }//GEN-LAST:event_wizardPage2BackActionPerformed

    private void wizardPage3BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wizardPage3BackActionPerformed
        wizardPage3.setVisible(false);
        wizardPage2.setVisible(true);
    }//GEN-LAST:event_wizardPage3BackActionPerformed

    private void wizardPage4BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wizardPage4BackActionPerformed
        wizardPage4.setVisible(false);
        
        if (useExistingRadio.isSelected ())
            wizardPage2.setVisible(true);
        else
            wizardPage3.setVisible(true);
    }//GEN-LAST:event_wizardPage4BackActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        SaveWorkspace sm = new SaveWorkspace (map, mapFile, presets, presetFile);
        sm.execute();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        // TODO add your handling code here:
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showDialog(this, "Save As") == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            SaveWorkspace sm = new SaveWorkspace (map, new File (fileChooser.getSelectedFile().getPath() + File.separator + ConfigFiles.MAP.getName()), presets, new File (fileChooser.getSelectedFile().getPath() + File.separator + ConfigFiles.CMD_PRESETS.getName()));
            sm.execute();
        }
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void preferencesItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preferencesItemActionPerformed
        // Set default preferances page:
        hideAllPreferencePanels();
        loadRoverPropertiesPage();
        
        propertiesPage.setLocationRelativeTo(null);
        propertiesPage.setVisible(true);
    }//GEN-LAST:event_preferencesItemActionPerformed

    private void roverPropertiesOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roverPropertiesOkActionPerformed
        // Check to make sure all data is correct:
        boolean ok = true;
        roverPropertiesErrorLabel.setForeground(Color.RED);
        roverPropertiesErrorLabel.setText("");
        
        if (roverPropertiesWidth.getText().isEmpty())
        {
            ok = false;
            roverPropertiesWidthLabel.setForeground(Color.RED);
        }
        else
            roverPropertiesWidthLabel.setForeground(Color.BLACK);
        
        if (roverPropertiesHeight.getText().isEmpty())
        {
            ok = false;
            roverPropertiesHeightLabel.setForeground(Color.RED);
        }
        else
            roverPropertiesHeightLabel.setForeground(Color.BLACK);
        
        if (roverPropertiesLength.getText().isEmpty())
        {
            ok = false;
            roverPropertiesLengthLabel.setForeground(Color.RED);
        }
        else
            roverPropertiesLengthLabel.setForeground(Color.BLACK);
        
        if (roverPropertiesX.getText().isEmpty())
        {
            ok = false;
            roverPropertiesXLabel.setForeground(Color.RED);
        }
        else
        {
            if (Integer.parseInt(roverPropertiesX.getText()) > map.getWidth())
            {
                roverPropertiesErrorLabel.setText("Given rover x value invalid.");
                roverPropertiesXLabel.setForeground(Color.red);
            }
            else
                roverPropertiesXLabel.setForeground(Color.BLACK);
        }
        
        if (roverPropertiesY.getText().isEmpty())
        {
            ok = false;
            roverPropertiesYLabel.setForeground(Color.RED);
        }
        else
        {
            if (Integer.parseInt(roverPropertiesY.getText()) > map.getWidth())
            {
                roverPropertiesErrorLabel.setText("Given rover x value invalid.");
                roverPropertiesYLabel.setForeground(Color.red);
            }
            else
                roverPropertiesYLabel.setForeground(Color.BLACK);
        }
        
        if (roverPropertiesHeading.getText().isEmpty())
        {
            ok = false;
            roverPropertiesHeadingLabel.setForeground(Color.RED);
        }
        else
            roverPropertiesHeadingLabel.setForeground(Color.BLACK);
        
        if (ok)
        {
            // Save buffered values and close
            roverPropertiesErrorLabel.setText("");
            rover.setX(Integer.parseInt(roverPropertiesX.getText()));
            rover.setY(Integer.parseInt(roverPropertiesY.getText()));
            rover.setWidth(Integer.parseInt(roverPropertiesWidth.getText()));
            rover.setHeight(Integer.parseInt(roverPropertiesHeight.getText()));
            rover.setLength(Integer.parseInt(roverPropertiesLength.getText()));
            rover.setDirection(Float.parseFloat(roverPropertiesHeading.getText()));
            
            propertiesPage.setVisible(false);
        }
    }//GEN-LAST:event_roverPropertiesOkActionPerformed

    private void roverPropertiesCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roverPropertiesCancelActionPerformed
        propertiesPage.setVisible(false);
    }//GEN-LAST:event_roverPropertiesCancelActionPerformed

    private void preset4ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preset4ButtonActionPerformed
        try
        {
            if (presets[3].getCommand().isEmpty()) // Do nothing if preset not yet set
                return;
            
            port.send(presets[3].getCommand(), (String) encodeCharsetSelector.getSelectedItem());
        }
        catch (SerialException ex) 
        {
            Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_preset4ButtonActionPerformed

    private void leftTurnCommandButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftTurnCommandButtonActionPerformed
        // TODO add your handling code here:
        // Make sure angle is valid
        if (!movementCommandAngle.getText().isEmpty())
        {
            movementCommandAngle.setForeground(Color.black);
            movementCommandDegLabel.setForeground(Color.black);
            
            // Send command
        }
        else
        {
            movementCommandAngle.setForeground(Color.red);
            movementCommandDegLabel.setForeground(Color.red);
        }
    }//GEN-LAST:event_leftTurnCommandButtonActionPerformed

    private void rightTurnCommandButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightTurnCommandButtonActionPerformed
        // TODO add your handling code here:
        // Make sure angle is valid
        if (!movementCommandAngle.getText().isEmpty())
        {
            movementCommandDegLabel.setForeground(Color.black);
            movementCommandAngle.setForeground(Color.black);
            
            // Send command
        }
        else
        {
            movementCommandDegLabel.setForeground(Color.red);
            movementCommandAngle.setForeground(Color.red);
        }
    }//GEN-LAST:event_rightTurnCommandButtonActionPerformed

    private void backwardsCommandButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backwardsCommandButtonActionPerformed
        // TODO add your handling code here:
        // Make sure distance is valid
        if (!movementCommandDistance.getText().isEmpty())
        {
            movementCommandDistance.setForeground(Color.black);
            movementCommandMM.setForeground(Color.black);
            
            // Send command
        }
        else
        {
            movementCommandDistance.setForeground(Color.red);
            movementCommandMM.setForeground(Color.red);
        }
    }//GEN-LAST:event_backwardsCommandButtonActionPerformed

    private void forwardCommandButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardCommandButtonActionPerformed
        // Make sure distance is valid
        if (!movementCommandDistance.getText().isEmpty())
        {
            movementCommandDistance.setForeground(Color.black);
            movementCommandMM.setForeground(Color.black);
            
            // Send command
            
        }
        else
        {
            movementCommandDistance.setForeground(Color.red);
            movementCommandMM.setForeground(Color.red);
        }
    }//GEN-LAST:event_forwardCommandButtonActionPerformed

    private void preferencePagesListValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_preferencePagesListValueChanged
        // TODO add your handling code here:
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) preferencePagesList.getLastSelectedPathComponent();

        if (node == null) // Nothing is selected
            return;
        
        switch (node.toString())
        {
            case "Rover":
                hideAllPreferencePanels();
                loadRoverPropertiesPage();
                break;
                
            case "Presets":
                hideAllPreferencePanels();
                loadPresetPropertiesPage();
                break;
        }
    }//GEN-LAST:event_preferencePagesListValueChanged

    private void hideAllPreferencePanels ()
    {
        roverPropertiesPanel.setVisible(false);
        presetPropertiesPanel.setVisible(false);
        presetsPage2.setVisible(false); // Do this to prevent overlap with presetsPage1
    }
    
    private void presetsNextPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presetsNextPageButtonActionPerformed
        presetsPage1.setVisible(false);
        presetsPage2.setVisible(true);
    }//GEN-LAST:event_presetsNextPageButtonActionPerformed

    private void presetsPreviousPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presetsPreviousPageButtonActionPerformed
        presetsPage2.setVisible(false);
        presetsPage1.setVisible(true);
    }//GEN-LAST:event_presetsPreviousPageButtonActionPerformed

    private void preset1NameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_preset1NameFieldFocusLost
        if (preset1NameField.getText().isEmpty())
            preset1NameField.setText("Preset 1");
    }//GEN-LAST:event_preset1NameFieldFocusLost

    private void preset2NameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_preset2NameFieldFocusLost
        // TODO add your handling code here:
        if (preset2NameField.getText().isEmpty())
            preset2NameField.setText("Preset 2");
    }//GEN-LAST:event_preset2NameFieldFocusLost

    private void preset3NameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_preset3NameFieldFocusLost
        // TODO add your handling code here:
        if (preset3NameField.getText().isEmpty())
            preset3NameField.setText("Preset 3");
    }//GEN-LAST:event_preset3NameFieldFocusLost

    private void savePresetsButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePresetsButton2ActionPerformed
        // TODO add your handling code here:
        savePresets();
    }//GEN-LAST:event_savePresetsButton2ActionPerformed

    /**
     * A function that saves the preset values in window components to there object
     * Invokes SaveWorkspace
     */
    private void savePresets()
    {
        // Set presets according to data in window components
        presets[0].setName(preset1NameField.getText());
        presets[0].setCommand(preset1CommandField.getText());
        presets[0].setDescription(preset1DescriptionField.getText());
        
        presets[1].setName(preset2NameField.getText());
        presets[1].setCommand(preset2CommandField.getText());
        presets[1].setDescription(preset2DescriptionField.getText());
        
        presets[2].setName(preset3NameField.getText());
        presets[2].setCommand(preset3CommandField.getText());
        presets[2].setDescription(preset3DescriptionField.getText());
        
        presets[3].setName(preset4NameField.getText());
        presets[3].setCommand(preset4CommandField.getText());
        presets[3].setDescription(preset4DescriptionField.getText());
        
        presets[4].setName(preset5NameField.getText());
        presets[4].setCommand(preset5CommandField.getText());
        presets[4].setDescription(preset5DescriptionField.getText());
        
        presets[5].setName(preset6NameField.getText());
        presets[5].setCommand(preset6CommandField.getText());
        presets[5].setDescription(preset6DescriptionField.getText());
        
        // Set button texts
        preset1Button.setText(presets[0].getName());
        preset2Button.setText(presets[1].getName());
        preset3Button.setText(presets[2].getName());
        preset4Button.setText(presets[3].getName());
        preset5Button.setText(presets[4].getName());
        preset6Button.setText(presets[5].getName());
        
        // Save workspace
        SaveWorkspace sw = new SaveWorkspace (map, mapFile, presets, presetFile);
        sw.execute();
        
        propertiesPage.setVisible(false);
    }
    
    private void savePresetsButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePresetsButton1ActionPerformed
        savePresets();
    }//GEN-LAST:event_savePresetsButton1ActionPerformed

    private void preset4NameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_preset4NameFieldFocusLost
        if (preset4NameField.getText().isEmpty())
            preset4NameField.setText("Preset 4");
    }//GEN-LAST:event_preset4NameFieldFocusLost

    private void preset5NameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_preset5NameFieldFocusLost
        if (preset5NameField.getText().isEmpty())
            preset5NameField.setText("Preset 5");
    }//GEN-LAST:event_preset5NameFieldFocusLost

    private void preset6NameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_preset6NameFieldFocusLost
        if (preset6NameField.getText().isEmpty())
            preset6NameField.setText("Preset 6");
    }//GEN-LAST:event_preset6NameFieldFocusLost

    private void preset1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preset1ButtonActionPerformed
        try
        {
            if (presets[0].getCommand().isEmpty()) // Do nothing if preset not yet set
                return;
            
            port.send(presets[0].getCommand(), (String) encodeCharsetSelector.getSelectedItem());
        }
        catch (SerialException ex) 
        {
            Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_preset1ButtonActionPerformed

    private void preset2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preset2ButtonActionPerformed
        try
        {
            if (presets[1].getCommand().isEmpty()) // Do nothing if preset not yet set
                return;
            
            port.send(presets[1].getCommand(), (String) encodeCharsetSelector.getSelectedItem());
        }
        catch (SerialException ex) 
        {
            Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_preset2ButtonActionPerformed

    private void preset3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preset3ButtonActionPerformed
        try
        {
            if (presets[2].getCommand().isEmpty()) // Do nothing if preset not yet set
                return;
            
            port.send(presets[2].getCommand(), (String) encodeCharsetSelector.getSelectedItem());
        }
        catch (SerialException ex) 
        {
            Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_preset3ButtonActionPerformed

    private void preset5ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preset5ButtonActionPerformed
        try
        {
            if (presets[4].getCommand().isEmpty()) // Do nothing if preset not yet set
                return;
            
            port.send(presets[4].getCommand(), (String) encodeCharsetSelector.getSelectedItem());
        }
        catch (SerialException ex) 
        {
            Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_preset5ButtonActionPerformed

    private void preset6ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preset6ButtonActionPerformed
        try
        {
            if (presets[5].getCommand().isEmpty()) // Do nothing if preset not yet set
                return;
            
            port.send(presets[5].getCommand(), (String) encodeCharsetSelector.getSelectedItem());
        }
        catch (SerialException ex) 
        {
            Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_preset6ButtonActionPerformed

    private void sendCommandButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendCommandButtonActionPerformed
        if (sendCommandField.getText().isEmpty()) // Do nothing if commmand field is empty
            return;
        
        try
        {
            port.send(sendCommandField.getText(), (String) encodeCharsetSelector.getSelectedItem());
        }
        catch (SerialException ex)
        {
            Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendCommandButtonActionPerformed

    @SuppressWarnings("UnnecessaryReturnStatement")
    private void findPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findPathButtonActionPerformed
        // Validate data entered
        boolean ok = true;
        
        pathfindErrorLabel.setText("");
        pathfindErrorLabel.setForeground(Color.red);
        
        // Check to make sure all fields have data and are valid.
        if (!pathfindStartXField.getText().isEmpty())
        {
            if (Integer.parseInt(pathfindStartXField.getText()) <= map.getWidth())
                pathfindStartXLabel.setForeground(Color.black);
            else
            {
                pathfindStartXLabel.setForeground(Color.red);
                pathfindErrorLabel.setText("Invalid data entered");
            }
        }
        else
        {
            ok = false;
            pathfindStartXLabel.setForeground(Color.red);
        }
        
        if (!pathfindStartYField.getText().isEmpty())
        {
            if (Integer.parseInt(pathfindStartYField.getText()) <= map.getLength())
                pathfindStartYLabel.setForeground(Color.black);
            else
            {
                pathfindStartYLabel.setForeground(Color.red);
                pathfindErrorLabel.setText("Invalid data entered");
            }
        }
        else
        {
            ok = false;
            pathfindStartYLabel.setForeground(Color.red);
        }
        
        if (!pathfindGoalXField.getText().isEmpty())
        {
            if (Integer.parseInt(pathfindGoalXField.getText()) <= map.getWidth())
                pathfindGoalXLabel.setForeground(Color.black);
            else
            {
                pathfindGoalXLabel.setForeground(Color.red);
                pathfindErrorLabel.setText("Invalid data entered");
            }
        }
        else
        {
            ok = false;
            pathfindGoalXLabel.setForeground(Color.red);
        }
        
        if (!pathfindGoalYField.getText().isEmpty())
        {
            if (Integer.parseInt(pathfindGoalYField.getText()) <= map.getLength())
                pathfindGoalYLabel.setForeground(Color.black);
            else
            {
                pathfindGoalYLabel.setForeground(Color.red);
                pathfindErrorLabel.setText("Invalid data entered");
            }
        }
        else
        {
            ok = false;
            pathfindGoalYLabel.setForeground(Color.red);
        }
        
        // Make sure the two coordinates are different:
        if (pathfindStartXField.getText().equals(pathfindGoalXField.getText()))
        {
            if (pathfindStartYField.getText().equals(pathfindGoalYField.getText()))
            {
                pathfindErrorLabel.setText("Two coordinates cannot be the same");
                ok = false;
            }
        }
        
        if (!ok) // Exit if data entered is invalid
            return;
        
        findPathButton.setEnabled(false);
        pathfindErrorLabel.setForeground(Color.black);
        
        Pathfinder pf = new Pathfinder (new Coord(Integer.parseInt(pathfindStartXField.getText()), Integer.parseInt(pathfindStartYField.getText())),
                new Coord(Integer.parseInt(pathfindGoalXField.getText()), Integer.parseInt(pathfindGoalYField.getText())), simpleMap, pathfindErrorLabel);
        
    }//GEN-LAST:event_findPathButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoteMapper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(RemoteMapper::new);
    }
    
    private static boolean existingWorkspaceValid (File ws)
    {
         return (new File (ws.getPath() + File.separator + ConfigFiles.MAP.getName()).isFile() && new File (ws.getPath() + File.separator + ConfigFiles.CMD_PRESETS.getName()).isFile());
    }
    
    private void cleanupWizard ()
    {
        wizardPage1.dispose();
        wizardPage2.dispose();
        wizardPage3.dispose();
        wizardPage4.dispose();
        loadingScreen.dispose();
    }
    
    private void loadMainFrame ()
    {
        cleanupWizard ();
        setTitle("Remote Mapper ["+workspace.getName()+"] on ["+port.portName()+"]");
        
        // Setup background functions
        TimeKeeper tk = new TimeKeeper (clock);
        tk.execute();
        
        AutoSaver as = new AutoSaver (map, mapFile, presetFile);
        as.execute();
        
        // Setup formatted textfields
        PlainDocument pd17 = (PlainDocument) movementCommandDistance.getDocument();
        pd17.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd18 = (PlainDocument) movementCommandAngle.getDocument();
        pd18.setDocumentFilter(new AngleFilter());
        
        PlainDocument pd19 = (PlainDocument) pathfindStartXField.getDocument();
        pd19.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd20 = (PlainDocument) pathfindStartYField.getDocument();
        pd20.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd21 = (PlainDocument) pathfindGoalXField.getDocument();
        pd21.setDocumentFilter(new MyIntFilter());
        
        PlainDocument pd22 = (PlainDocument) pathfindGoalYField.getDocument();
        pd22.setDocumentFilter(new MyIntFilter());
        
        propertiesPage.setAlwaysOnTop(true);
        
        // Set preset button texts
        preset1Button.setText(presets[0].getName());
        preset2Button.setText(presets[1].getName());
        preset3Button.setText(presets[2].getName());
        preset4Button.setText(presets[3].getName());
        preset5Button.setText(presets[4].getName());
        preset6Button.setText(presets[5].getName());
        
        setVisible (true);
    }
    
    private void loadRoverPropertiesPage ()
    {
        // Load current values:
        roverPropertiesWidth.setText(""+rover.getWidth());
        roverPropertiesHeight.setText(""+rover.getHeight());
        roverPropertiesLength.setText(""+rover.getWidth());
        roverPropertiesHeading.setText((""+rover.getDirection()).length() >= 4 ? (String)(""+rover.getDirection()).subSequence(0, 4) : (""+rover.getDirection()));
        roverPropertiesX.setText(""+rover.getX());
        roverPropertiesY.setText(""+rover.getY());
        
        roverPropertiesPanel.setVisible(true);
    }
    
    private void loadPresetPropertiesPage()
    {
        // Load presets into window components
        preset1NameField.setText(presets[0].getName());
        preset1CommandField.setText(presets[0].getCommand());
        preset1DescriptionField.setText(presets[0].getDescription());
        
        preset2NameField.setText(presets[1].getName());
        preset2CommandField.setText(presets[1].getCommand());
        preset2DescriptionField.setText(presets[1].getDescription());
        
        preset3NameField.setText(presets[2].getName());
        preset3CommandField.setText(presets[2].getCommand());
        preset3DescriptionField.setText(presets[2].getDescription());
        
        preset4NameField.setText(presets[3].getName());
        preset4CommandField.setText(presets[3].getCommand());
        preset4DescriptionField.setText(presets[3].getDescription());
        
        preset5NameField.setText(presets[4].getName());
        preset5CommandField.setText(presets[4].getCommand());
        preset5DescriptionField.setText(presets[4].getDescription());
        
        preset6NameField.setText(presets[5].getName());
        preset6CommandField.setText(presets[5].getCommand());
        preset6DescriptionField.setText(presets[5].getDescription());
        
        presetPropertiesPanel.setVisible(true);
        presetsPage1.setVisible(true); // Show default page
    }

    //<editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame about;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JCheckBoxMenuItem autoSaveCheckBox;
    private javax.swing.JButton backwardsCommandButton;
    private javax.swing.JComboBox<String> baudRateSelector;
    private javax.swing.JButton browseButton;
    private javax.swing.JLabel clock;
    private javax.swing.JComboBox<String> decodeCharsetSelector;
    private javax.swing.JLabel emptySpaceMarkLabel;
    private javax.swing.JTextField emptySpaceMarkTextField;
    private javax.swing.JComboBox<String> encodeCharsetSelector;
    private javax.swing.JLabel errorJLabel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton findPathButton;
    private javax.swing.JButton forwardCommandButton;
    private javax.swing.JFormattedTextField headingFormattedField;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JButton leftTurnCommandButton;
    private javax.swing.JTextArea loadingConsole;
    private javax.swing.JProgressBar loadingProgressBar;
    private javax.swing.JFrame loadingScreen;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JFormattedTextField mapHeightFormattedField;
    private javax.swing.JLabel mapHeightLabel;
    private javax.swing.JFormattedTextField mapWidthFormattedField;
    private javax.swing.JLabel mapWidthLabel;
    private javax.swing.JTextField movementCommandAngle;
    private javax.swing.JLabel movementCommandDegLabel;
    private javax.swing.JTextField movementCommandDistance;
    private javax.swing.JLabel movementCommandMM;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel obsticalMarkLabel;
    private javax.swing.JTextField obsticalMarkTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox<String> pathFindAlgorithmSelector;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JLabel pathfindErrorLabel;
    private javax.swing.JTextField pathfindGoalXField;
    private javax.swing.JLabel pathfindGoalXLabel;
    private javax.swing.JTextField pathfindGoalYField;
    private javax.swing.JLabel pathfindGoalYLabel;
    private javax.swing.JTextField pathfindStartXField;
    private javax.swing.JLabel pathfindStartXLabel;
    private javax.swing.JTextField pathfindStartYField;
    private javax.swing.JLabel pathfindStartYLabel;
    private javax.swing.JCheckBox pathfindUseRoverPos;
    private javax.swing.JComboBox<String> portComboBox;
    private javax.swing.JLabel positionErrorLabel;
    private javax.swing.JFormattedTextField positionXFormattedField;
    private javax.swing.JLabel positionXLabel;
    private javax.swing.JFormattedTextField positionYFormattedField;
    private javax.swing.JLabel positionYLabel;
    private javax.swing.JTree preferencePagesList;
    private javax.swing.JMenuItem preferencesItem;
    private javax.swing.JButton preset1Button;
    private javax.swing.JTextField preset1CommandField;
    private javax.swing.JTextArea preset1DescriptionField;
    private javax.swing.JTextField preset1NameField;
    private javax.swing.JButton preset2Button;
    private javax.swing.JTextField preset2CommandField;
    private javax.swing.JTextArea preset2DescriptionField;
    private javax.swing.JTextField preset2NameField;
    private javax.swing.JButton preset3Button;
    private javax.swing.JTextField preset3CommandField;
    private javax.swing.JTextArea preset3DescriptionField;
    private javax.swing.JTextField preset3NameField;
    private javax.swing.JButton preset4Button;
    private javax.swing.JTextField preset4CommandField;
    private javax.swing.JTextArea preset4DescriptionField;
    private javax.swing.JTextField preset4NameField;
    private javax.swing.JButton preset5Button;
    private javax.swing.JTextField preset5CommandField;
    private javax.swing.JTextArea preset5DescriptionField;
    private javax.swing.JTextField preset5NameField;
    private javax.swing.JButton preset6Button;
    private javax.swing.JTextField preset6CommandField;
    private javax.swing.JTextArea preset6DescriptionField;
    private javax.swing.JTextField preset6NameField;
    private javax.swing.JPanel presetPropertiesPanel;
    private javax.swing.JLayeredPane presetsLayeredPane;
    private javax.swing.JButton presetsNextPageButton;
    private javax.swing.JPanel presetsPage1;
    private javax.swing.JPanel presetsPage2;
    private javax.swing.JButton presetsPreviousPageButton;
    private javax.swing.JButton previewRouteButton;
    private javax.swing.JLayeredPane propertiesLayers;
    private javax.swing.JFrame propertiesPage;
    private javax.swing.JButton rightTurnCommandButton;
    private javax.swing.JFormattedTextField roverHeightFormattedField;
    private javax.swing.JLabel roverHeightLabel;
    private javax.swing.JFormattedTextField roverLengthFormattedField;
    private javax.swing.JLabel roverLengthLabel;
    private javax.swing.JButton roverPropertiesCancel;
    private javax.swing.JLabel roverPropertiesErrorLabel;
    private javax.swing.JFormattedTextField roverPropertiesHeading;
    private javax.swing.JLabel roverPropertiesHeadingLabel;
    private javax.swing.JFormattedTextField roverPropertiesHeight;
    private javax.swing.JLabel roverPropertiesHeightLabel;
    private javax.swing.JFormattedTextField roverPropertiesLength;
    private javax.swing.JLabel roverPropertiesLengthLabel;
    private javax.swing.JButton roverPropertiesOk;
    private javax.swing.JPanel roverPropertiesPanel;
    private javax.swing.JFormattedTextField roverPropertiesWidth;
    private javax.swing.JLabel roverPropertiesWidthLabel;
    private javax.swing.JFormattedTextField roverPropertiesX;
    private javax.swing.JLabel roverPropertiesXLabel;
    private javax.swing.JFormattedTextField roverPropertiesY;
    private javax.swing.JLabel roverPropertiesYLabel;
    private javax.swing.JFormattedTextField roverWidthFormattedField;
    private javax.swing.JLabel roverWidthLabel;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton savePresetsButton1;
    private javax.swing.JButton savePresetsButton2;
    private javax.swing.JPanel selectPagePanel;
    private javax.swing.JButton sendCommandButton;
    private javax.swing.JTextField sendCommandField;
    private javax.swing.JButton start;
    private javax.swing.JPanel statusBar;
    private javax.swing.JTable statusDeviceTable;
    private javax.swing.JLabel statusPosHeading;
    private javax.swing.JLabel statusPosX;
    private javax.swing.JLabel statusPosY;
    private javax.swing.JRadioButton useDefaultMarkingsRadio;
    private javax.swing.JRadioButton useExistingRadio;
    private javax.swing.JFrame wizardPage1;
    private javax.swing.JFrame wizardPage2;
    private javax.swing.JButton wizardPage2Back;
    private javax.swing.JLabel wizardPage2ErrorLabel;
    private javax.swing.JFrame wizardPage3;
    private javax.swing.JButton wizardPage3Back;
    private javax.swing.JFrame wizardPage4;
    private javax.swing.JButton wizardPage4Back;
    private javax.swing.JLabel wizardPage4Header2;
    private javax.swing.JButton wizardPage4Next;
    private javax.swing.JLabel wizardPage4Title;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
    
    /**
    *   A class that keeps time. Runs every second.
    */
    class TimeKeeper extends SwingWorker<Void, Void>
    {
        private JLabel display;
        
        public TimeKeeper (JLabel display)
        {
            this.display = display;
        }
        
        @Override
        @SuppressWarnings("SleepWhileInLoop")
        protected Void doInBackground() {
            for (; ;)
            {
                // Update clock
                display.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm.ss")));
                
                // Update rover status
                statusPosX.setText(""+rover.getX());
                statusPosY.setText(""+rover.getY());
                statusPosHeading.setText(""+rover.getDirection());
                
                try 
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
    * A class that saves the workspace every set time interval.
    */
    class AutoSaver extends SwingWorker<Void, Void>
    {
        File mapFile, presetsFile;
        Map map;
        SaveWorkspace sm;
        static final int AUTOSAVE_INTERVAL = 15000;
        
        public AutoSaver (Map map, File mapFile, File presetsFile)
        {
            this.mapFile = mapFile;
            this.presetsFile = presetsFile;
            this.map = map; 
        }

        @Override
        @SuppressWarnings("SleepWhileInLoop")
        protected Void doInBackground()
        {
            for (; ;)
            {
                try 
                {
                    Thread.sleep(AUTOSAVE_INTERVAL);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (autoSaveCheckBox.isSelected() && !propertiesPage.isVisible())
                {
                    sm = new SaveWorkspace (this.map, this.mapFile, presets, this.presetsFile);
                    sm.execute();
                }
            }
        }   
    }
    
    class LoadWorkspace extends SwingWorker<Map, Integer>
    {
        private final File mapFile, presetFile;
        private CommandPreset[] presets;

        LoadWorkspace (File mapFile, CommandPreset[] presets, File presetFile)
        {
            this.mapFile = mapFile;
            this.presets = presets;
            this.presetFile = presetFile;
        }
        
        @Override
        protected void process (List<Integer> chunks)
        {
            int i = chunks.get(chunks.size()-1);
            loadingProgressBar.setValue(i);
        }

        @Override
        @SuppressWarnings("null")
        public Map doInBackground() throws LoadWorkspaceException
        {
            FileReader in = null;
            try 
            {
                // Load command presets
                ArrayList<CommandPreset> cps = CommandPreset.loadPresets(presetFile);
                cps.sort(CommandPreset.CommandPresetComparator);
                
                
                for (int e = 0; e < this.presets.length; e++)
                {
                    this.presets[e] = cps.get(e); // Load them in the correct order
                }
                
                // Load map
                in = new FileReader(this.mapFile);
                int c, y = 0;
                
                // Buffer map
                ArrayList<ArrayList<Character>> b = new ArrayList();
                b.add(new ArrayList<>());
                
                char obM = (char) in.read();
                in.skip(1);
                char esM = (char) in.read();
                in.skip(1);
                
                int i = 4;
                
                while ((c = in.read()) != -1)
                {
                    if (c == '\n')
                    {
                        y++;
                        b.add(new ArrayList<>());
                    }
                    else
                    {
                        b.get(y).add((char) c);
                    }
                    
                    if (++i % 100 == 0)
                        publish (i);
                }   
                in.close();
                
                int capX = b.get(0).size() + 1;
                int capY = b.size() -1;
                char[][] m = new char[capY][capX];
                
                for (y = 0; y < capY; y++)
                {
                    for (int x = 0; x < capX - 1; x++)
                    {
                        m[y][x] = b.get(y).get(x);
                    }
                    m[y][capX - 1] = 10;
                }   
                
                return new Map (m, obM, esM);
            }
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex)
            {
                Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try 
                {
                    in.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            throw new LoadWorkspaceException();
        }
        
        @Override
        public void done ()
        {
            try
            {
                map = get();
                loadingConsole.append("Done...\n");
                
                loadingScreen.setVisible(false);
                loadMainFrame();
            }
            catch (InterruptedException | ExecutionException ex)
            {
                Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    /*
     * A swing worker that saves the workspace.
     */
    class SaveWorkspace extends SwingWorker <Void, Integer>
    {
        private JProgressBar jpb;
        private JTextArea log;
        private final Map map;
        private final CommandPreset[] presets;
        private final File presetsFile, mapFile;
        
        public SaveWorkspace (Map map, File mapFile, CommandPreset[] presets, File presetsFile, JProgressBar jpb, JTextArea log)
        {
            this.map = map;
            this.mapFile = mapFile;
            this.presets = presets;
            this.presetsFile = presetsFile;
            this.jpb = jpb;
            this.log = log;
        }
        
        public SaveWorkspace (Map map, File mapFile, CommandPreset[] presets, File presetsFile)
        {
            this.map = map;
            this.mapFile = mapFile;
            this.presets = presets;
            this.presetsFile = presetsFile;
        }

        @Override
        @SuppressWarnings("null")
        protected Void doInBackground()
        {
            FileWriter mapFileWriter = null;
            
            try
            {
                // Save commandPresets:
                int e = 0;

                for (CommandPreset cp1 : presets) 
                {
                    cp1.saveToFile(presetsFile);
                    publish(++e);
                }
                
                // Save map
                final char[][] d = RemoteMapper.this.map.getMap();
                
                // Set jProgressBar max value
                if (jpb != null)
                    jpb.setMaximum(d.length * d[0].length);
                
                mapFileWriter = new FileWriter(this.mapFile.getCanonicalPath(), false);
                
                // Write markers
                mapFileWriter.write (String.valueOf(RemoteMapper.this.map.getObsticalMark()) + "\n");
                mapFileWriter.write (String.valueOf(RemoteMapper.this.map.getEmptySpaceMark()) + "\n");
                
                int i = 0;
                // Write map raw data
                for (char[] d1 : d) {
                    for (int x = 0; x < d1.length; x++) {
                        mapFileWriter.write(d1[x]);
                        publish (++i);
                    }
                }
                
                mapFileWriter.flush();
                mapFileWriter.close();
                
                // Configure window components
                if (log != null)
                    log.append("Saving workspace... [2/2]\n");
                if (jpb != null)
                    jpb.setMaximum(presets.length);
            } 
            catch (IOException ex) 
            { 
                Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try 
                {
                    mapFileWriter.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return null;
        }
        
        @Override
        protected void process (List<Integer> chunks)
        {
            int i = chunks.get(chunks.size()-1);
            if (jpb != null)
                jpb.setValue(i);
        }
        
        @Override
        protected void done ()
        {
            // Write to log
            if (log != null)
            {
                log.append ("Done...\n");
                
                // Load mainframe
                if (log.equals(loadingConsole))
                {
                    loadingScreen.setVisible(false);
                    loadMainFrame();
                }
            }
        }
        
    }
    
    class Pathfinder extends SwingWorker<Node[], Void>
    {
        private final Coord start, goal;
        private JLabel display;
        private final Map map;
        
        public Pathfinder (Coord start, Coord goal, Map map)
        {
            this.start = start;
            this.goal = goal;
            this.map = map;
        }
        
        /**
         * 
         * @param start
         * @param goal
         * @param map Map to search for route on
         * @param display 
         */
        public Pathfinder (Coord start, Coord goal, Map map, JLabel display)
        {
            this.start = start;
            this.goal = goal;
            this.display = display;
            this.map = map;
        }

        @Override
        protected Node[] doInBackground() throws Exception {
            if (display != null)
                display.setText("Pathfinding...");
            
            return map.Astar(new Node(start.getX(), start.getY()), new Node(goal.getX(), goal.getY()));
        }
        
        @Override
        protected void done ()
        {
            Node[] path = null;
            try {
                path = get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(RemoteMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (display != null)
            {
                if (path != null)
                    display.setText("Route found!");
                else
                    display.setText("No route found!");
            }
        }
    }
}