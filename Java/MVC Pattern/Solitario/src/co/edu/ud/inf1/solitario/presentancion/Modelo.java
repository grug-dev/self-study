/**
 * 
 */
package co.edu.ud.inf1.solitario.presentancion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import co.edu.ud.inf1.solitario.logica.Carta;
import co.edu.ud.inf1.solitario.logica.Logica;
import co.edu.ud.inf1.solitario.logica.Slot;
import co.edu.ud.inf1.solitario.presentacion.util.IConstantes;

/**
 * @author RaspuWIN7
 *
 */
public class Modelo {

	private VistaPrincipal vistaPpal;
	private Logica logica;
	private List<Carta> allCards;
	private int regAntDragged = -1;
	private boolean regSupAnt ;

	/**
	 * Constructor por defecto
	 */
	public Modelo() {
		super();
	}
	
	/**
	 * Método que obtiene la totalidad de las cartas
	 * usadas en el juego de solitario
	 * @return List<Carta> Listado de cartas
	 */
	public List<Carta> obtenerBaraja() {
		return getLogica().obtenerBaraja();
	}

	/**
	 * Inicializa la ventana principal del
	 * juego solitario, repartiendo las cartas
	 * en la parte inferior.
	 */
	public void iniciarSolitario() {
		reparticionCartas(obtenerBaraja());
		getVistaPpal().setSize(IConstantes.ANCHO_FRAME, IConstantes.ALTO_FRAME);
		getVistaPpal().setResizable(false);
		getVistaPpal().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		getVistaPpal().setVisible(true);
	}


	/**
	 * Método que reparte las cartas randomicamente
	 * en la parte inferior del tablero de solitario.
	 * @param pAllCards List<Carta> listado de cartas a repartir
	 */
	public void reparticionCartas(List<Carta> pAllCards) {
		allCards = pAllCards;
		Map<Integer, List<Carta>> mapRegionesInferiores = null;
		mapRegionesInferiores = getLienzo().getMapRegionesInferiores();
		for (int reg = 0; reg <= 6; reg++) {
			if (mapRegionesInferiores.get(reg) != null) {
				mapRegionesInferiores.put(reg, null);
			}
			Random rnd = new Random();
			int numRandom = -1;
			List<Carta> lstCarta = null;
			Carta cartaRandom = null;
			for (int i = 0; i <= reg; i++) {
				while (cartaRandom == null) {
					numRandom = (int) (rnd.nextDouble() * 51 + 1);
					cartaRandom = allCards.get(numRandom);
				}
				if (cartaRandom != null) {
					allCards.set(numRandom, null);
					if (mapRegionesInferiores.get(reg) == null) {
						mapRegionesInferiores.put(reg, new ArrayList<Carta>());
					}
					if (i == reg) {
						cartaRandom.setVisible(true);
					}
					lstCarta = mapRegionesInferiores.get(reg);
					lstCarta.add(cartaRandom);
					mapRegionesInferiores.put(reg, lstCarta);
					cartaRandom = null;
					continue;
				}
			}
		}
		getLienzo().setMapRegionesInferiores(mapRegionesInferiores);
		verificacionCartasDisponibles();
	}

	/**
	 * Método para eliminar las cartas nulas ya usadas
	 * del listado de cartas original, y las vuelve almacenar
	 * en la nueva lista de cartas
	 */
	public void verificacionCartasDisponibles() {
		Set<Carta> setCartas = new HashSet<Carta>();

		setCartas.addAll(getAllCards());
		setCartas.remove(null);

		getAllCards().clear();
		getAllCards().addAll(setCartas);
	}
	
	/**
	 * Método que cambia la carta del stock
	 * de cartas disponibles para jugar
	 * en la parte superior izquierda.
	 */
	public void cambiarCartaStock() {
		List<Carta> allCards = getAllCards();

		if (allCards == null || allCards.isEmpty()) {
			return;
		}

		int proxCarta = -1;
		if (getLienzo().getActualCartaVisibleStock() == null) {
			proxCarta = 0;
		} else {
			proxCarta = allCards.indexOf(getLienzo().getActualCartaVisibleStock());
			proxCarta++;
		}
		definirCartaStock(proxCarta);
		getLienzo().repaint();
	}
	
	/**
	 * Método para definir la próxima carta a mostrar
	 * en el stock basado en un indice de la lista
	 * de cartas disponibles a usar
	 * @param proxCarta int indice de la lista de cartas
	 * disponibles
	 */
	private void definirCartaStock(int proxCarta) {
		int sizeLst = -1;
		sizeLst = getAllCards().size();

		if (proxCarta < 0 || (proxCarta == sizeLst)) {
			proxCarta = 0;
		}
		
		if (getAllCards().isEmpty()) {
			getLienzo().setActualCartaVisibleStock(null);
			return;
		}

		Carta c = getAllCards().get(proxCarta);
		c.setVisible(true);
		getLienzo().setActualCartaVisibleStock(c);
	}

	/**
	 * Método de asignación rápida de carta producto de un
	 * doble click. Realiza la validación correspondiente
	 * al juego para saber si existe una posible asignación
	 * de la carta que fue clickeada.
	 * @param region int Número de region donde fue producido el evento del click.
	 * @param x int Pixel horizonal donde fue producido el evento del click.
	 * @param y int Pixel vertical donde fue producido el evento del click.
	 * @param regSup boolean true si fue en la región superior, false si fue
	 * región inferior.
	 */
	public void asignacionAutomatica(int region, int x , int y , boolean regSup) {
		Map<Integer, List<Carta>> mapRegSup = null;
		List<Carta> lstCartasReg = null;
		Carta cartaMover = null;
		Slot slot = null;

		lstCartasReg = regSup ? getAllCards() : getLienzo().getMapRegionesInferiores().get(region);
		mapRegSup = getLienzo().getMapSlots();

		if (lstCartasReg == null) {
			return;
		}
		
		List<Carta> cartasMove = findCardsToMove(region, lstCartasReg, x, y, regSup);
		if (cartasMove != null && !cartasMove.isEmpty()) {
			cartaMover = cartasMove.get(0);
		}

		if (cartaMover == null) {
			return;
		}
		
		slot = getLogica().validacionAsignaciónASlot(mapRegSup, cartaMover);
		

		if (slot != null && slot.getNumSlot() != -1) {
			cartaMover.setPosY(IConstantes.PXLY_REGION_SUPERIOR);
			switch (slot.getNumSlot()) {
			case 3:
				cartaMover.setPosX(IConstantes.PXLX_REG_3);
				break;
			case 4:
				cartaMover.setPosX(IConstantes.PXLX_REG_4);
				break;
			case 5:
				cartaMover.setPosX(IConstantes.PXLX_REG_5);
				break;
			case 6:
				cartaMover.setPosX(IConstantes.PXLX_REG_6);
				break;
			}

			removerCartaMovida(cartasMove, region, regSup);
			cartaMover.setVisible(true);
			slot.getLstCartasXSlot().add(cartaMover);
			mapRegSup.put(slot.getNumSlot(), slot.getLstCartasXSlot());
			getLienzo().setMapSlots(mapRegSup);
			getLienzo().repaint();
		}
	}
	
	/**
	 * Método que encuentra las cartas involucradas en algún evento
	 * basandose en las posiciones x,y del evento, la región dónde fue
	 * producido el evento, y una lista de cartas de la región
	 * donde actualmente ocurrió el evento.
	 * 
	 * @param region int Número de region donde fue producido el evento.
	 * @param lstCartasReg Lista de cartas de la región donde ocurrio el evento
	 * @param x int Pixel horizonal donde fue producido el evento.
	 * @param y int Pixel vertical donde fue producido el evento.
	 * @param regSup boolean true si fue en la región superior, false si fue
	 * 				 región inferior.
	 * @return List<Carta> Listado de cartas encontradas producto del evento.
	 */
	
	 
	private List<Carta> findCardsToMove(int region, List<Carta> lstCartasReg, int x, int y , boolean regSup) {
		List<Carta> cartaMoverLst = null;
		Carta cartaMov = null;
		Carta actualCartaAsignar = null;
		int posEncontrada = -1;
		cartaMoverLst = new ArrayList<Carta>();
		actualCartaAsignar = getLienzo().getActualCartaVisibleStock();
		
		if (lstCartasReg == null) {
			return null;
		}

		// Encontrar carta que se desea mover
		if (region == 1 && actualCartaAsignar != null && regSup) {
			cartaMoverLst.add(actualCartaAsignar);
		} else {
			int total = lstCartasReg.size();
			int posL = total -1 ;
			while (posL >= 0) {
				Carta c = lstCartasReg.get(posL);
				if (!c.isVisible()) {
					continue;
				}
				if (y >= c.getPosY()) {
					posEncontrada = posL;
					break;
				}
				posL --;
			}
			
			if (posEncontrada != -1 ) {
				for (int pos = posEncontrada ; pos < total ; pos++) {
					Carta c =  lstCartasReg.get(pos);
					if (c.isVisible()) {
						c.setVisible(false);
						cartaMov = c.clone();
						cartaMov.setPosX(x);
						cartaMov.setPosY(y);
						cartaMov.setVisible(true);
						cartaMoverLst.add(cartaMov);
					}
				}
			}
		}

		return cartaMoverLst;
	}
	
	/**
	 * Método que remueve un listado de cartas que se están moviendo actualmente 
	 * en el tablero, para evitar que se visualize dos veces 
	 * en el tablero.
	 * @param lstCartRemoved List<Carta> listado de cartas a borrar o desdibujar en el tablero.
	 * @param region int region donde se encuentran las cartas a borrar
	 * @param regSup boolean true indicando que fue en la region superior, false inferior.
	 */
	private void removerCartaMovida(List<Carta> lstCartRemoved, int region , boolean regSup) {
		List<Carta> lstCarta = null;
		List<Carta> lstCartaDel = new ArrayList<Carta>();
		int posCartaStock = -1;
		lstCarta = encontrarCartasXRegion(region , regSup);

		if (lstCarta == null || lstCarta.isEmpty()) {
			return;
		}
		
		int total = lstCarta.size();
		Carta c = null;
		for (Carta cartaToRemove : lstCartRemoved) {
			for (int pos = 0; pos < total; pos++) {
				c = lstCarta.get(pos);
				if (c.compareTo(cartaToRemove) == 0) {
					posCartaStock = pos;
					lstCartaDel.add(c);
					break;
				}
			}
		}

		for (Carta cartaDel : lstCartaDel) {
			lstCarta.remove(cartaDel);
		}

		if (region == 1 && regSup) {
			setAllCards(lstCarta);
			if (getLienzo().getActualCartaVisibleStock() != null) {
				int proxCarta = getAllCards().indexOf(getLienzo().getActualCartaVisibleStock());
				definirCartaStock(proxCarta);
			}
			else {
				definirCartaStock(posCartaStock);
			}
			return;
		}
		
		if (regSup) {
			getLienzo().getMapSlots().put(region, lstCarta);	
		}else {
			getLienzo().getMapRegionesInferiores().put(region, lstCarta);
		}
	}
	
	/**
	 * Método que busca cuales son las cartas que actualmente
	 * se encuentran asignadas a una determinada región.
	 * @param region int Número de region a buscar
	 * @param regSup boolean true si es región superior, false inferior.
	 * @return List<Carta> Listado de cartas encontradas.
	 */
	private List<Carta> encontrarCartasXRegion(int region , boolean regSup) {
		List<Carta> lstCarta = null;
		Map<Integer, List<Carta>> mapRegiones = null;
		
		if (region == 1 && regSup) {
			lstCarta = getAllCards();
		} else {
			mapRegiones = regSup ? getLienzo().getMapSlots() : getLienzo().getMapRegionesInferiores();
			lstCarta = mapRegiones.get(region);
		}

		return lstCarta;
	}
	
	
	/**
	 * Método que se ejecuta cuando se desea mover
	 * una carta.
	 * @param x int Posicion horizonal en pxl del tablero dónde se produjo el evento.
	 * @param y int Posicion vertical en pxl del tablero dónde se produjo el evento.
	 * @param region int Número de region  dónde se produjo el evento.
	 * @param regSup boolean true si es región superior, false inferior.
	 */
	public void moverCarta(int x, int y, int region , boolean regSup) {
		List<Carta> lstCartasReg, lstCartaMove = null;

		if (getLienzo().getLstCartasEnMov() != null && !getLienzo().getLstCartasEnMov().isEmpty()) {
			lstCartaMove = getLienzo().getLstCartasEnMov();
			for (Carta c : lstCartaMove) {
				c.setPosX(x);
				c.setPosY(y);
			}
		} else {
			lstCartasReg = encontrarCartasXRegion(region , regSup);
			lstCartaMove = findCardsToMove(region, lstCartasReg, x, y , regSup);

			if (lstCartaMove == null || lstCartaMove.isEmpty()) {
				return;
			}
		}
		if (regAntDragged == -1) {
			regAntDragged = region;
			regSupAnt = regSup;
		}
		removerCartaMovida(lstCartaMove, region , regSup);
		getLienzo().setLstCartasEnMov(lstCartaMove);
		getLienzo().repaint();
	}
	
	/**
	 * Método dónde permite dibujar la carta soltada
	 * en una región determinada
	 * @param region int número de region donde se desea soltar la carta
	 * @param pRegSUp boolean True region superior, false inferior.
	 */
	public void soltarCarta(int region , boolean pRegSUp) {
		List<Carta> lstCarta = null;
		boolean movValido = false;
		if (getLienzo().getLstCartasEnMov() == null) {
			return;
		}
		
		// Validar 
		movValido =  isMovimientoValido(region, pRegSUp);
		regAntDragged = movValido ? region : regAntDragged;
		regSupAnt = movValido ? pRegSUp : regSupAnt;
		
		if (regSupAnt && regAntDragged == 1 && !movValido) {
			getAllCards().addAll(getLienzo().getLstCartasEnMov());
			int proxCarta = getAllCards().size() - 1;
			definirCartaStock(proxCarta);
		}
		else {
			lstCarta = encontrarCartasXRegion(regAntDragged, regSupAnt);
			if (lstCarta == null) {
				lstCarta = new ArrayList<Carta>();
			}
			lstCarta.addAll(getLienzo().getLstCartasEnMov());
			if (!pRegSUp) {
				getLienzo().getMapRegionesInferiores().put(regAntDragged, lstCarta);	
			}
			else if (movValido) {
				getLienzo().getMapSlots().put(regAntDragged, lstCarta);
			}
			
		}
		
		regAntDragged = -1;
		regSupAnt = false;
		getLienzo().setLstCartasEnMov(null);
		getLienzo().repaint();
	}
	
	/**
	 * Método para validar si el movimiento de un carta es válido o no,
	 * según a la región dónde se desee soltar.
	 * @param regAValidar int número de región dónde se soltó la carta
	 * @param pRegSup true si es región superior, false inferior
	 * @return boolean True si el movimiento es válido, false si fue inválido.
	 */
	private boolean isMovimientoValido (int regAValidar , boolean pRegSup ) {
		Carta cartaMoviendo = null;
		List<Carta> lstCartaRegion = null;
		
		lstCartaRegion = encontrarCartasXRegion(regAValidar, pRegSup);
		cartaMoviendo = getLienzo().getLstCartasEnMov().get(0);
		
		return getLogica().isMovimientoValido(regAValidar, pRegSup, cartaMoviendo, lstCartaRegion);
		
	}
	
	/** GETTER & SETTER */

	private VistaPrincipal getVistaPpal() {
		if (vistaPpal == null) {
			vistaPpal = new VistaPrincipal(this);
		}
		return vistaPpal;
	}

	/**
	 * Método que obtiene el lienzo
	 * del tablero donde se dibuja el solitario
	 * @return Tablero Objeto
	 */
	private Tablero getLienzo() {
		return getVistaPpal().getLienzo();
	}

	/**
	 * Obtiene Objeto Logica
	 * @return the logica
	 */
	private Logica getLogica() {
		if (logica == null) {
			logica = new Logica();
		}
		return logica;
	}

	/**
	 * Obtiene 
	 * @return the allCards
	 */
	public List<Carta> getAllCards() {
		return allCards;
	}

	/**
	 * Asigna
	 * @param allCards the allCards to set
	 */
	public void setAllCards(List<Carta> allCards) {
		this.allCards = allCards;
	}

}
