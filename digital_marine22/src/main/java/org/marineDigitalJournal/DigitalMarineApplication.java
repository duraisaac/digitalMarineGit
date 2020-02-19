package org.marineDigitalJournal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.jfree.ui.tabbedui.VerticalLayout;
import org.marineDigitalJournal.neuralnet.DisplayData_Thread;
import org.marineDigitalJournal.neuralnet.DisplayProduct;
import org.marineDigitalJournal.neuralnet.Display_Product_Thread;
import org.marineDigitalJournal.neuralnet.DownloadMotu;
import org.marineDigitalJournal.neuralnet.Downloadind_Sat_NRT_Data;
import org.marineDigitalJournal.neuralnet.TrainModel;
import org.marineDigitalJournal.neuralnet.Trainning_Model;
import org.marineDigitalJournal.neuralnet.UseModelScript;
import org.marineDigitalJournal.presentation.swing.BackgroundImage;
import org.marineDigitalJournal.presentation.swing.BackgroundImageCHL_predictions_next_day;
import org.marineDigitalJournal.presentation.swing.BackgroundImagePredictions_high_CHL_locations;
import org.marineDigitalJournal.presentation.swing.BackgroundImage_Product;
import org.marineDigitalJournal.presentation.swing.ImageFrame;
import org.marineDigitalJournal.presentation.swing.Login;
import org.marineDigitalJournal.presentation.swing.NextPage;
import org.marineDigitalJournal.presentation.swing.Register;

public class DigitalMarineApplication extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static String limitCHL = "";

	private static JFrame frame;

	static private JButton predictionsHighCHLocationsButton, chl_predictions_next_day_button;
	static private JButton database_button, register_button;
	static private JButton funding_button, about_button;
	static private JButton maps_button, exit_button;

	static private JMenuItem predictions_high_CHL_locations, chl_predictions_next_day;
	static private JMenuItem collect, database, about;
	static private JMenuItem funding, exit;

	// slider
	static JSlider b;

	public DigitalMarineApplication() {

		frame = new JFrame("Blue Maritime Digital Journal App ");

		try {
			initComponents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setLayout(null);

		frame.setSize(screenSize.width, screenSize.height);

		addMenu();

		BackgroundImage background = new BackgroundImage();
		background.setSize(screenSize.width, screenSize.height);

		background.add(addButtons(), BorderLayout.CENTER);

		frame.setContentPane(background);

	}

	private void initComponents() throws IOException {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		setTitle("File");
		
		new Thread() {
			public void run() {
				
				System.out.println("1. downlaod data --------->");
				new DownloadMotu().downloadSatNear_Real_TimeData();

				System.out.println("2. train model  --------->");
				new TrainModel().train_model();
				
				System.out.println("3. display product --------->");
				new DisplayProduct().display();
				
				System.out.println("4. use_model.py ---->");
				new UseModelScript().display();
			};
		}.start();
			
	}

	private JPanel addButtons() {

		/* Add buttons */

		JPanel parent = new JPanel(new VerticalLayout());
		parent.setBounds(frame.getWidth() / 4, 400, frame.getWidth() / 2, frame.getHeight() / 2);
		
		parent.setOpaque(false);

		Color font_color = Color.BLUE;
		int thick = 25;

		predictionsHighCHLocationsButton = new JButton("Predictions_high_CHL_locations_high_biomass_blooms");
		predictionsHighCHLocationsButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		predictionsHighCHLocationsButton.setForeground(font_color);
		predictionsHighCHLocationsButton.addActionListener(this);
		parent.add(predictionsHighCHLocationsButton);

		chl_predictions_next_day_button = new JButton("CHL_predictions_next_day_high_biomass_blooms");
		chl_predictions_next_day_button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chl_predictions_next_day_button.setForeground(font_color);
		chl_predictions_next_day_button.addActionListener(this);
		parent.add(chl_predictions_next_day_button);

		maps_button = new JButton("Maps");
		maps_button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		maps_button.setForeground(font_color);
		maps_button.addActionListener(this);
		parent.add(maps_button);

		register_button = new JButton("Collect");
		register_button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		register_button.setForeground(font_color);
		register_button.addActionListener(this);
		parent.add(register_button);

		database_button = new JButton("Database");
		database_button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		database_button.setForeground(font_color);
		database_button.addActionListener(this);
		parent.add(database_button);

		funding_button = new JButton("Funding");
		funding_button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		funding_button.setForeground(font_color);
		funding_button.addActionListener(this);
		parent.add(funding_button);

		about_button = new JButton("About");
		about_button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		about_button.setForeground(font_color);
		about_button.addActionListener(this);
		parent.add(about_button);

		exit_button = new JButton("Exit");
		exit_button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exit_button.setForeground(font_color);
		exit_button.addActionListener(this);
		parent.add(exit_button);

		return parent;
	}

	private void addMenu() {
		JMenu menu;

		JMenuBar mb = new JMenuBar();

		menu = new JMenu("Home");

		predictions_high_CHL_locations = new JMenuItem("Predictions_high_CHL_locations_high_biomass_blooms");
		chl_predictions_next_day = new JMenuItem("CHL_predictions_next_day_high_biomass_blooms");
		collect = new JMenuItem("Collect");
		database = new JMenuItem("Database");

		about = new JMenuItem("About");
		funding = new JMenuItem("Funding");
		exit = new JMenuItem("Exit");

		menu.add(predictions_high_CHL_locations).addActionListener(this);

		menu.add(chl_predictions_next_day).addActionListener(this);

		menu.add("Maps").addActionListener(this);

		menu.add(collect).addActionListener(this);

		menu.add(database).addActionListener(this);

		menu.add(about).addActionListener(this);

		menu.add(funding).addActionListener(this);

		exit.addActionListener(this);

		menu.add(exit);

		mb.add(menu);

		frame.setJMenuBar(mb);

	}

	// method actionPerformed()
	// to get the action performed
	// by the user and act accordingly
	public void actionPerformed(ActionEvent ev) {
		
		if (ev.getActionCommand().equals("Exit")) {
			System.exit(0);
		} else if (ev.getActionCommand().equals("Funding")) {

			NextPage page = new NextPage();

			page.setTitle("Funding");

			JLabel jLabel = new JLabel();

			String text = "<HTML><BODY style='marging-left:100 marging-right:100; padding:100'><font size = 5 color=black>This application has been developed within the EOVALUE project, \n"
					+ "which has received funding from the European Union’s Horizon 2020 research and innovation programme.\n"
					+ "The JRC, or as the case may be the European Commission, shall not be held liable for any direct or indirect,\n"
					+ "incidental, consequential or other damages, including but not limited to the loss of data, loss of profits, \n"
					+ "or any other financial loss arising from the use of this application, or inability to use it, even if the JRC \n"
					+ "is notified of the possibility of such damage. <BR></font></BODY></HTML>";

			jLabel.setText(text);

			page.setBounds(300, 200, 800, 500);

			page.add(jLabel);

			page.getContentPane().setBackground(Color.lightGray);

			page.setSize(700, 700);

			page.setBounds(100, 500, 800, 800);

			page.setVisible(true);

		} else if (ev.getActionCommand().equals("About")) {

			NextPage page = new NextPage();

			page.setTitle("About");
			JLabel jLabel = new JLabel();

			String test = "<HTML><BODY style='marging-left:100 marging-right:100; padding:100'><font size= 4 color=black>Marine earth observation satellites continuously observe the dynamics of the marine ecosystems variables \n"
					+ "like surface temperature, salinity, anomalies in the surface of the ocean, Chlorophyll-a or plankton concentration. This data is delivered to users \n"
					+ "providing a source of valuable information to environmental experts, scientists and citizens.\n"
					+ "CMEMS and Copernicus Satellite data on maritime change are freely available on global level and are integrated in the open data initiative of the \n"
					+ "European Commission and the Group on Earth Observation (GEO).\n"
					+ "The Blue Marine Digital Journal App is part of the EOVALUE project on EO based innovative apps in environmental and social domains and addresses \n"
					+ "the identification and prediction of maritime hazards and automated alerts to inform journalists and general users on near-real-time, adding \n"
					+ "informative transparency to the general population.\n"
					+ "The proposed system uses artificial intelligence algorithms feeded by Copernicus Marine Environmental Monitoring System data which has been validated as optimal by CMEMS.\n"
					+ "Users can monitor near-real-time maritime dynamics like concentration of Chlorophyll at an European dimension supporting the early identification of Harmful Algal Blooms.\n"
					+ "The data used is retrieved from Sentinel 3 A|B, other satellites data is provided b the Copernicus Marine Environmental Monitoring System \n"
					+ "Blue Marine Digital Journal app also integrates crowdsourcing functionalities for maritime hazards observations by the \n"
					+ "individual user."
					+ "Users of Blue Marine Digital Journal app make use and support the maritime observation services of the European Union’s Earth monitoring programme Copernicus.  \n"
					+ "The application allows the use machine learning for early detection of strong concentrations of Chlorophyll-a/high-biomass blooms and potentially HABs for predicting \n"
					+ "and monitoring its occurrence.\n"
					+ " With the app users can participate in building and establishing an in-situ database for maritime hazards information and compare their individual observations with \n"
					+ "satellite measurements. The user can explore marine maps of European marine dynamics.\n"
					+ "The alpha version of the application was designed and developed by Isaac Dura founder of the award-winning European Space Agency Business Incubator Center Darmstadt startup HeraSpace. \n"
					+ "The satellite data provided by the Copernicus Marine Environmental monitoring system (CMEMS) containing merged sources from ESA and NASA.</font></BODY></HTML>";

			jLabel.setText(test);

			page.setBounds(300, 200, 800, 500);

			page.add(jLabel);

			page.getContentPane().setBackground(Color.lightGray);

			page.setSize(700, 700);

			page.setBounds(100, 500, 800, 800);

			page.setVisible(true);

		} else if (ev.getActionCommand().equals("Database")) {

			Login l = new Login();

		} else if (ev.getActionCommand().equals("Predictions_high_CHL_locations_high_biomass_blooms")) {
			
			String imageFile = "org/marineDigitalJournal/presentation/swing/predictions_high_CHL_locations.png";
			displayImage(imageFile);

		} else if (ev.getActionCommand().equals("CHL_predictions_next_day_high_biomass_blooms")) {
			displayImage("org/marineDigitalJournal/presentation/swing/predictions_CHL_next_day.png");
			

		} else if (ev.getActionCommand().equals("Maps")) {
		
			displayImage("org/marineDigitalJournal/presentation/swing/current_CHL_concentrations.png");

		} else if (ev.getActionCommand().equals("Collect")) {
			Register f = new Register();
		} else if (ev.getActionCommand().equals("Registration")) {
			Register f = new Register();
		}
	}

	private ImageFrame displayImage(String imageFile) {
		ImageFrame page = new ImageFrame("Predictions high CHL locations high biomass blooms", imageFile);
		page.setVisible(true);
		return page;
	}

	public static void main(String[] args) throws IOException, MessagingException {

		new DigitalMarineApplication().setVisible(true);

		frame.setLayout(null);

		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

}