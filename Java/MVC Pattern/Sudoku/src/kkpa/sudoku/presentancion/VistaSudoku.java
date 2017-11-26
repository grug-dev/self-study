package kkpa.sudoku.presentancion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import kkpa.sudoku.presentancion.controladores.ControladorAcciones;
import kkpa.sudoku.presentancion.controladores.ControladorCronometro;
import kkpa.sudoku.presentancion.controladores.ControladorNumerico;
import kkpa.sudoku.presentancion.controladores.ControladorTablero;
import kkpa.sudoku.utilidades.SudokuConstantes;

public class VistaSudoku extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -3513148657144479466L;
    
    /** Constantes */
	private static final String TITULO_PRG = "SUDOKU-KKPA";
	private static final String TITULO_TABLERO = "SuDoKu";
	private static final String TITULO_CONTROLES = "Acciones de Juego";
	private static final int SIZE_CELL = 50;
	private static final int SIZE_WIDTH_ACCIONES = 120;
	private static final int SIZE_HEIGHT_ACCIONES = 30;
	private static final int ALTURA_PNL_OPCIONES = 180;
	private static final int SEPARACION_BOTONES = 10;
	private static final int SIZE_FONT_TIEMPO = 30;
	private static final Color COLOR_TIEMPO = Color.BLACK;
	private static final Font FUENTE_CELDA = new Font(Font.SERIF, Font.BOLD, 25);
	private static final int ANCHO_FRAME = 740;
	private static final int ALTO_FRAME = 610;
	
	/** Componentes */
	private JLabel lblTitulo;
	private JPanel pnlTiempo;
	private JPanel pnlCentral;
	private JPanel pnlTablero;
	private JPanel pnlNumerico;
	private JPanel pnlControles;
	private JButton celdaSelected;
	private JButton btnComprobar;
	private JButton btnReiniciar;
	private JButton btnGuardar;
	private JButton btnIniciarTiempo;
	private JButton btnPausarTiempo;
	private JButton btnReiniciarTiempo;
	private JLabel lbltiempo;
	private JButton[][] mtzCeldas;

	/** Controladores */
	private ControladorTablero controlTablero;
	private ControladorNumerico controlPnlNumerico;
	private ControladorAcciones controlAcciones;
	private ControladorCronometro controlCronometro;

	private final ModeloPpal modeloPpal;
	private Timer timerCronometro;
	private int segundos = 0;
	private String h0 = "00", m0 = "00", s0 = "00";

	/**
	 * Constructor
	 * 
	 * @param pModelo
	 *            ModeloPpal Modelo de la vista
	 */
	public VistaSudoku(ModeloPpal pModelo) {
		super(TITULO_PRG);
		this.modeloPpal = pModelo;
	}

	/**
	 * Método encargado de construir los componentes necesarios para iniciar el
	 * juego de sudoku.
	 */
	public void inicializarJuego() {
		construirCuadricula();
		agregarComponentes();
		startUI();
	}
	
	private void startUI() {
		setSize(ANCHO_FRAME, ALTO_FRAME);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
				getTimerCronometro().stop();
				System.exit(0);
			}
		});
	}

	/**
	 * Método encargado de construir la cuadricula con sus respectivas celdas de
	 * una manera dinamica.
	 */
	public void construirCuadricula() {
		getPnlTablero().removeAll();
		GridBagConstraints gbc = new GridBagConstraints();
		for (int i = 0; i < SudokuConstantes.SIZE_SUDOKU; i++) {
			for (int j = 0; j < SudokuConstantes.SIZE_SUDOKU; j++) {
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.gridx = j;
				gbc.gridy = i;
				getPnlTablero().add(getMtzCeldas()[i][j], gbc);
			}
		}
		getPnlTablero().repaint();
	}

	/**
	 * Agrega los componentes gráficos de la vista del juego del sudoku al
	 * contenedor principal del Frame
	 */
	private void agregarComponentes() {
		this.setLayout(new BorderLayout());
		this.add(getLblTitulo(), BorderLayout.NORTH);
		this.add(getPnlCentral(), BorderLayout.CENTER);
		this.add(getPnlTiempo(), BorderLayout.EAST);
		this.add(getPnlControles(), BorderLayout.WEST);
	}

	/**
	 * Método que estandariza las propiedades que debe tener una celda en la
	 * cuadricula del tablero del sudoku. La celda a modificar es la pasada como
	 * parametro, además se asigna un nombre a la celda para diferenciarlas de
	 * las demás ya que se construyen de una manera dinamica.
	 * 
	 * @param celda
	 *            JButton Objeto con la información de la celda.
	 * @param name
	 *            String nombre que se desea darle a la celda.
	 */
	private void agregarPropiedadesCelda(JButton celda, String name) {
		celda.setPreferredSize(new Dimension(SIZE_CELL, SIZE_CELL));
		celda.setName(name);
		celda.setBackground(SudokuConstantes.COLOR_DEFECTO);
		celda.addActionListener(getControlTablero());
	}
	
	public void asignarPuntoPartida() {
		Font fontDefault = new Font(Font.SERIF, Font.BOLD, 25);
		List<String> dataInicial = null;
		
		dataInicial = getModeloPpal().leerArchivo(SudokuConstantes.NOM_ARCHIVO_INICIAL);
		
		for (String value : dataInicial) {
			String[] data = value.split(";");
			int posX = Integer.valueOf(data[0]);
			int posY = Integer.valueOf(data[1]);
			String val = data[2];
			getMtzCeldas()[posX][posY].setText(val);
			getMtzCeldas()[posX][posY].setFont(fontDefault);
			getMtzCeldas()[posX][posY].setForeground(Color.BLACK);
			getMtzCeldas()[posX][posY].setEnabled(false);	
		}
	}
	
	/**
	 * Metodo para asignar las propiedades de la celda
	 * como seleccionada
	 * @param celda JButton Objeto
	 */
	public void cambiarPropCeldaSeleccionada(JButton celda) {
		celda.setForeground(Color.RED);
		celda.setFont(FUENTE_CELDA);
		celda.setBackground(SudokuConstantes.COLOR_DEFECTO);
	}
	
	/**
	 * Mostrar un mensaje al usuario basadao en el parametro 
	 * @param mensaje String mensaje a mostrar al usuario
	 */
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	/**
	 * Obtener el modelo de la vista
	 * 
	 * @return ModeloPpal Objeto
	 */
	private ModeloPpal getModeloPpal() {
		return modeloPpal;
	}

	/**
	 * Método que obtiene el nombre de la celda basados en los parametros de
	 * entrada.
	 * 
	 * @param i
	 *            Posicion X de la celda en la cuadricula
	 * @param j
	 *            Posicion Y de la celda en al cuadricula
	 * @return String nombre de la celda
	 */
	private String obtenerNombreCelda(int i, int j) {
		return "[" + i + "," + j + "]";
	}

	/**
	 * Método encargado de aumentar el tiempo del cronometro visto en la ventana
	 * del juego.
	 */
	private String aumentarTiempoCronometro() {
		Integer horas, minutos;
		horas = Integer.parseInt(h0);
		minutos = Integer.parseInt(m0);
		String hor = h0, min = m0, seg;

		segundos++;

		if (segundos == 60) {
			segundos = 0;
			minutos += 1;
		}
		if (minutos == 60) {
			minutos = 0;
			horas++;
		}

		if (horas < 10)
			hor = "0" + horas;
		else
			hor = horas.toString();

		if (minutos < 10)
			min = "0" + minutos;
		else
			min = minutos.toString();

		if (segundos < 10)
			seg = "0" + segundos;
		else
			seg = String.valueOf(segundos);

		h0 = hor;
		m0 = min;
		s0 = seg;

		return hor + ":" + min + ":" + seg;

	}

	/** GETTERS COMPONENTES */

	/**
	 * Método que obtiene un objeto JButton, él cuál representa una celda de la
	 * cuadricula del juego de sudoku.
	 * 
	 * @param nameCelda
	 *            String Nombre que se le va asignar a la celda.
	 * @return JButton Objeto celda
	 */
	private JButton getBtnCelda(String nameCelda) {
		JButton btnCelda = new JButton();
		agregarPropiedadesCelda(btnCelda, nameCelda);
		return btnCelda;
	}

	/**
	 * Método que obtiene el componente comprobar juego sudoku.
	 * 
	 * @return JButton Objeto
	 */
	private JButton getBtnComprobar() {
		if (btnComprobar == null) {
			btnComprobar = new JButton();
			btnComprobar.setText(SudokuConstantes.COMPROBAR);
			btnComprobar.setName(SudokuConstantes.COMPROBAR);
			btnComprobar.setSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnComprobar.setPreferredSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnComprobar.addActionListener(getControlAcciones());
		}
		return btnComprobar;
	}

	/**
	 * Método que obtiene el componente guardar juego sudoku.
	 * 
	 * @return JButton Objeto
	 */
	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton();
			btnGuardar.setText(SudokuConstantes.GUARDAR);
			btnGuardar.setName(SudokuConstantes.GUARDAR);
			btnGuardar.setSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnGuardar.setPreferredSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnGuardar.addActionListener(getControlAcciones());
		}
		return btnGuardar;
	}

	/**
	 * Método que obtiene un componente tipo JButton, él cuál representa un
	 * número, qué será añadido al panel de opciones númericas a asignar a la
	 * cuadricula del juego
	 * 
	 * @int vlrNumero valor del numero
	 * @return JButton Objeto
	 */
	private JButton getBtnNumero(int vlrNumero) {
		JButton btnNum = new JButton();
		btnNum.setName("" + vlrNumero);
		btnNum.setText("" + vlrNumero);
		btnNum.addActionListener(getControlPnlNumerico());
		return btnNum;
	}

	/**
	 * Método que obtiene el componente reiniciar juego sudoku.
	 * 
	 * @return JButton Objeto
	 */
	private JButton getBtnReiniciar() {
		if (btnReiniciar == null) {
			btnReiniciar = new JButton();
			btnReiniciar.setText(SudokuConstantes.REINICIAR);
			btnReiniciar.setName(SudokuConstantes.REINICIAR);
			btnReiniciar.setPreferredSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnReiniciar.setSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnReiniciar.addActionListener(getControlAcciones());
		}
		return btnReiniciar;
	}

	/**
	 * Método que obtiene el componente JButton relacionado al botón reset del
	 * panel de controles.
	 * 
	 * @return JButton Objeto
	 */
	private JButton getBtnReset() {
		JButton btnReset = new JButton();
		btnReset.setName(SudokuConstantes.RESET);
		btnReset.setText(SudokuConstantes.RESET);
		btnReset.addActionListener(getControlPnlNumerico());
		return btnReset;
	}

	/**
	 * Método que obtiene la celda actual que el usuario selecciono para
	 * modificar su valor.
	 * 
	 * @return JButton Objeto
	 */
	public JButton getCeldaSelected() {
		return celdaSelected;
	}

	/**
	 * Método que asigna la celda actualmente seleccionada en la cuadricula
	 * 
	 * @param celdaSelected
	 */
	public void setCeldaSelected(JButton celdaSelected) {
		this.celdaSelected = celdaSelected;
	}

	/**
	 * Obtiene el controlador definido para las acciones del juego como guardar
	 * juego , comprobar juego, reinicar juego
	 * 
	 * @return ControladorAcciones Objeto
	 */
	private ControladorAcciones getControlAcciones() {
		if (controlAcciones == null) {
			controlAcciones = new ControladorAcciones(this);
		}
		return controlAcciones;
	}

	/**
	 * Obtiene el controlador para el panel de controles numericos que se
	 * asignan a las celdas de la cuadricula
	 * 
	 * @return ControladorNumerico Objeto
	 */
	private ControladorNumerico getControlPnlNumerico() {
		if (controlPnlNumerico == null) {
			controlPnlNumerico = new ControladorNumerico(this);
		}
		return controlPnlNumerico;
	}

	/**
	 * Obtiene el controlador del tablero o cuadricula. Está encargado de
	 * responde a lo eventos de las celdas.
	 * 
	 * @return ControladorTablero Objeto
	 */
	private ControladorTablero getControlTablero() {
		if (controlTablero == null) {
			controlTablero = new ControladorTablero(this);
		}
		return controlTablero;
	}

	/**
	 * Obtiene el título del juego sudoku.
	 * 
	 * @return
	 */
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel(TITULO_TABLERO, JLabel.CENTER);
			lblTitulo.setBorder(new LineBorder(Color.BLUE));
		}
		return lblTitulo;
	}

	/**
	 * Obtiene la matriz de celdas qué serán añadidas a la cuadricula del
	 * tablero sudoku
	 * 
	 * @return JButton[][] Matriz de componentes JButton
	 */
	public JButton[][] getMtzCeldas() {
		if (mtzCeldas == null) {
			String name = null;
			mtzCeldas = new JButton[SudokuConstantes.SIZE_SUDOKU][SudokuConstantes.SIZE_SUDOKU];
			for (int i = 0; i < SudokuConstantes.SIZE_SUDOKU; i++) {
				for (int j = 0; j < SudokuConstantes.SIZE_SUDOKU; j++) {
					name = obtenerNombreCelda(i, j);
					mtzCeldas[i][j] = getBtnCelda(name);
				}
			}
		}
		return mtzCeldas;
	}

	/**
	 * Método para asignar una matriz de componentes JButton, a la matriz actual
	 * contenida en el juego de sudoku. La nueva matriz, se le añadirán los
	 * comportamientos a cada una de las celdas definidas para el juego.
	 * 
	 * @param pMtzCeldas
	 *            JButton[][] Nueva matriz asignar al tablero.
	 */
	public void setMtzCeldas(JButton[][] pMtzCeldas) {
		mtzCeldas = pMtzCeldas;

		if (pMtzCeldas == null) {
			return;
		}
		for (int i = 0; i < SudokuConstantes.SIZE_SUDOKU; i++) {
			for (int j = 0; j < SudokuConstantes.SIZE_SUDOKU; j++) {
				JButton celda = pMtzCeldas[i][j];
				agregarPropiedadesCelda(celda, obtenerNombreCelda(i, j));
				cambiarPropCeldaSeleccionada(celda);
			}
		}
	}

	/**
	 * Obtiene un panel que contendrá los componentes de la cuadricula y el
	 * panel de control numerico
	 * 
	 * @return JPanel Objeto
	 */
	private JPanel getPnlCentral() {
		if (pnlCentral == null) {
			pnlCentral = new JPanel();
			pnlCentral.add(getPnlTablero());
			pnlCentral.add(getPnlNumerico());
		}
		return pnlCentral;
	}

	/**
	 * Obtiene el panel contenedor de las acciones disponibles a utilizar en el
	 * juego
	 * 
	 * @return JPanel Objeto
	 */
	private JPanel getPnlControles() {
		if (pnlControles == null) {
			pnlControles = new JPanel();
			JPanel pnlX = new JPanel();
			pnlX.setBorder(BorderFactory.createTitledBorder(TITULO_CONTROLES));
			pnlX.setLayout(new GridLayout(3, 1,SEPARACION_BOTONES,SEPARACION_BOTONES));
			pnlX.add(getBtnReiniciar());
			pnlX.add(getBtnComprobar());
			pnlX.add(getBtnGuardar());
			pnlControles.setLayout(new BoxLayout(pnlControles, BoxLayout.Y_AXIS));
			pnlControles.add(Box.createVerticalStrut(ALTURA_PNL_OPCIONES));
			pnlControles.add(pnlX);
			pnlControles.add(Box.createVerticalStrut(ALTURA_PNL_OPCIONES));
			
		}
		return pnlControles;
	}

	/**
	 * Obtiene el panel contenedor de los controles numericos a asignar a las
	 * celdas.
	 * 
	 * @return JPanel
	 */
	public JPanel getPnlNumerico() {
		if (pnlNumerico == null) {
			pnlNumerico = new JPanel();
			pnlNumerico.setLayout(new GridLayout(2, 5));
			for (int i = 1; i <= SudokuConstantes.SIZE_SUDOKU; i++) {
				pnlNumerico.add(getBtnNumero(i));
			}
			pnlNumerico.add(getBtnReset());
			pnlNumerico.setVisible(false);
		}
		return pnlNumerico;
	}

	/**
	 * Obtiene el componente contenedor de la cuadricula
	 * 
	 * @return JPanel objeto
	 */
	private JPanel getPnlTablero() {
		if (pnlTablero == null) {
			pnlTablero = new JPanel();
			pnlTablero.setBorder(new LineBorder(Color.GREEN));
			pnlTablero.setLayout(new GridBagLayout());
		}
		return pnlTablero;
	}

	/**
	 * Obtiene el contenedor del cronometro del juego
	 * 
	 * @return Jpanel
	 */
	private JPanel getPnlTiempo() {
		if (pnlTiempo == null) {
			pnlTiempo = new JPanel();
			JPanel pnlX = new JPanel();
			pnlX.setLayout(new GridLayout(4, 1,SEPARACION_BOTONES,SEPARACION_BOTONES));
			pnlX.add(getLblTiempo());
			pnlX.add(getBtnIniciarTiempo());
			pnlX.add(getBtnPausarTiempo());
			pnlX.add(getBtnReiniciarTiempo());
			pnlTiempo.setLayout(new BoxLayout(pnlTiempo, BoxLayout.Y_AXIS));
			pnlTiempo.add(Box.createVerticalStrut(ALTURA_PNL_OPCIONES));
			pnlTiempo.add(pnlX);
			pnlTiempo.add(Box.createVerticalStrut(ALTURA_PNL_OPCIONES));
		}
		return pnlTiempo;
	}

	/**
	 * Obtiene el componente que muestra el tiempo actual del cronometro del
	 * juego
	 * 
	 * @return JLabel objeto
	 */
	private JLabel getLblTiempo() {
		if (lbltiempo == null) {
			lbltiempo = new JLabel(h0 + ":" + m0 + ":" + s0);
			lbltiempo.setFont(new Font(Font.SERIF, Font.BOLD, SIZE_FONT_TIEMPO));
			lbltiempo.setHorizontalAlignment(JLabel.CENTER);
			lbltiempo.setForeground(COLOR_TIEMPO);
			lbltiempo.setOpaque(true);
		}
		return lbltiempo;
	}

	/**
	 * Obtiene el objeto Timer que controla el cronometro de luego.
	 * 
	 * @return Timer Objeto
	 */
	public Timer getTimerCronometro() {
		if (timerCronometro == null) {
			timerCronometro = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String tiempo = aumentarTiempoCronometro();
					getLblTiempo().setText(tiempo);
					getPnlTiempo().revalidate();
					getPnlTiempo().repaint();
				}
			});
		}
		return timerCronometro;
	}

	public JButton getBtnIniciarTiempo() {
		if (btnIniciarTiempo == null) {
			btnIniciarTiempo = new JButton();
			btnIniciarTiempo.setName(SudokuConstantes.INICIAR_TIEMPO);
			btnIniciarTiempo.setText(SudokuConstantes.INICIAR_TIEMPO);
			btnIniciarTiempo.setSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnIniciarTiempo.setPreferredSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnIniciarTiempo.addActionListener(getControlCronometro());
		}
		return btnIniciarTiempo;
	}

	public JButton getBtnPausarTiempo() {
		if (btnPausarTiempo == null) {
			btnPausarTiempo = new JButton();
			btnPausarTiempo.setText(SudokuConstantes.PAUSAR_TIEMPO);
			btnPausarTiempo.setName(SudokuConstantes.PAUSAR_TIEMPO);
			btnPausarTiempo.setPreferredSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnPausarTiempo.setSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnPausarTiempo.addActionListener(getControlCronometro());
			btnPausarTiempo.setEnabled(false);
		}
		return btnPausarTiempo;
	}

	public JButton getBtnReiniciarTiempo() {
		if (btnReiniciarTiempo == null) {
			btnReiniciarTiempo = new JButton();
			btnReiniciarTiempo.setText(SudokuConstantes.REINICIAR_TIEMPO);
			btnReiniciarTiempo.setName(SudokuConstantes.REINICIAR_TIEMPO);
			btnReiniciarTiempo.setSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnReiniciarTiempo.setPreferredSize(new Dimension(SIZE_WIDTH_ACCIONES, SIZE_HEIGHT_ACCIONES));
			btnReiniciarTiempo.addActionListener(getControlCronometro());
			btnReiniciarTiempo.setEnabled(false);
		}
		return btnReiniciarTiempo;
	}

	private ControladorCronometro getControlCronometro() {
		if (controlCronometro == null) {
			controlCronometro = new ControladorCronometro(this);
		}
		return controlCronometro;
	}

	/**
	 * Obtiene los segundos actuales del cronometro
	 * 
	 * @return int numero de segundos
	 */
	public int getSegundos() {
		return segundos;
	}
	
	public boolean  guardarPartida() {
		return getModeloPpal().guardar(getMtzCeldas());
	}
	
	public String comprobarPartida() {
		boolean result = getModeloPpal().comprobar(getMtzCeldas());
		
		return result ? "Felicitaciones!" : "El sudoku está mal hecho";
		
	}
	
	
	
	public void resetarCelda() {
		getCeldaSelected().setText(null);
		getPnlNumerico().setVisible(false);
		getCeldaSelected().setBackground(SudokuConstantes.COLOR_DEFECTO);
	}
	
	public void asignarNumeroACeldaSeleccionada(String texto) {
		getCeldaSelected().setText(texto);
		cambiarPropCeldaSeleccionada(getCeldaSelected());
		getPnlNumerico().setVisible(false);
	}
	
	public void reiniciarSudoku() {
		setMtzCeldas(null);
		construirCuadricula();
		asignarPuntoPartida();
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void iniciarCronometro() {
		getTimerCronometro().start();
		getBtnIniciarTiempo().setEnabled(false);
		getBtnPausarTiempo().setEnabled(true);
		getBtnReiniciarTiempo().setEnabled(false);
	}
	
	public void reiniciarCronometro() {
		getTimerCronometro().restart();
		getBtnReiniciarTiempo().setEnabled(false);
		getBtnIniciarTiempo().setEnabled(false);
		getBtnPausarTiempo().setEnabled(true);
	}
	
	public void pausarCronometro() {
		getTimerCronometro().stop();
		getBtnIniciarTiempo().setEnabled(false);
		getBtnReiniciarTiempo().setEnabled(true);
		getBtnPausarTiempo().setEnabled(false);
	}
	
	public void asignarCeldaSeleccionada(JButton celdaSeleccionada) {
		if (getCeldaSelected() != null) {
			getCeldaSelected().setBackground(SudokuConstantes.COLOR_DEFECTO);
		}
		setCeldaSelected(celdaSeleccionada);
		getCeldaSelected().setBackground(SudokuConstantes.COLOR_SELECCION);
		getPnlNumerico().setVisible(true);
	}

}