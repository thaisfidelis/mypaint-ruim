package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class Paint extends MouseAdapter {

	public JToolBar barraDeFerramentas;
	public JPanel areaDeDesenho;
	public List<Figura> figuras;
	public Figura tmp;
	public ButtonGroup grupo;
	public JToggleButton botaoSelecionado;
	public JToggleButton botaoRetangulo;
	public JToggleButton botaoElipse;
	public JToggleButton botaoLinha;

	private int x0;
	private int y0;
	
	public static void main(String[] args) {
		Paint programa = new Paint();
	}

	public Paint() {
		JFrame janela = new JFrame();
		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		
		this.figuras = new ArrayList<Figura>();
		
		//barraDeFerramentas = new BarraDeFerramentas();
		
		barraDeFerramentas = new JToolBar("Barra de Ferramentas");
		grupo = new ButtonGroup();
		botaoRetangulo = new JToggleButton("Retangulo");
		botaoRetangulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				botaoSelecionado = botaoRetangulo;
			}
		});
		grupo.add(botaoRetangulo);
		barraDeFerramentas.add(botaoRetangulo);
		botaoRetangulo.getModel().setPressed(true);
		
		botaoElipse = new JToggleButton("Elipse");
		botaoElipse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				botaoSelecionado = botaoElipse;
			}
		});
		grupo.add(botaoElipse);
		barraDeFerramentas.add(botaoElipse);
		botaoElipse.getModel().setPressed(false);
		
		botaoLinha = new JToggleButton("Linha");
		botaoLinha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				botaoSelecionado = botaoLinha;
			}
		});
		grupo.add(botaoLinha);
		barraDeFerramentas.add(botaoLinha);
		botaoLinha.getModel().setPressed(false);
		
		botaoSelecionado = botaoRetangulo;
		
		barraDeFerramentas.setFloatable(false);
		
		
		painelPrincipal.add(barraDeFerramentas, BorderLayout.PAGE_START);
		
		areaDeDesenho = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				for (Figura f : figuras) {
					switch (f.tipo) {
					case Figura.RETANGULO:
						g.drawRect(f.x, f.y, f.x2 - f.x, f.y2 - f.y);
						break;
					case Figura.ELIPSE:
						g.drawOval(f.x, f.y, f.x2 - f.x, f.y2 - f.y);
						break;
					case Figura.LINHA:
						g.drawLine(f.x, f.y, f.x2, f.y2);
						break;
					}
				}
				if (tmp != null) {
					switch (tmp.tipo) {
					case Figura.RETANGULO:
						g.drawRect(tmp.x, tmp.y, tmp.x2 - tmp.x, tmp.y2 - tmp.y);
						break;
					case Figura.ELIPSE:
						g.drawOval(tmp.x, tmp.y, tmp.x2 - tmp.x, tmp.y2 - tmp.y);
						break;
					case Figura.LINHA:
						g.drawLine(tmp.x, tmp.y, tmp.x2, tmp.y2);
						break;
					}
				}
			}
		};
		painelPrincipal.add(areaDeDesenho, BorderLayout.CENTER);
		
		areaDeDesenho.setPreferredSize(new Dimension(600, 400));
		
		areaDeDesenho.addMouseListener(this);
		areaDeDesenho.addMouseMotionListener(this);
		
		janela.setContentPane(painelPrincipal);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.x0 = e.getX();
		this.y0 = e.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x1 = e.getX();
		int y1 = e.getY();
		if (botaoSelecionado == botaoRetangulo) {
			int xc = Math.min(this.x0, x1);
			int yc = Math.min(this.y0, y1);
			int largura = Math.abs(x1 - this.x0);
			int altura = Math.abs(y1 - this.y0);
			tmp = new Figura(Figura.RETANGULO, xc, yc, xc + largura, yc + altura);
		} else if (botaoSelecionado == botaoLinha) {
			tmp = new Figura(Figura.LINHA, this.x0, this.y0, x1, y1);
		} else {
			int xc = Math.min(this.x0, x1);
			int yc = Math.min(this.y0, y1);
			int largura = Math.abs(x1 - this.x0);
			int altura = Math.abs(y1 - this.y0);
			tmp = new Figura(Figura.ELIPSE, xc, yc, xc + largura, yc + altura);
		}
		areaDeDesenho.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		int x1 = e.getX();
		int y1 = e.getY();
		Figura f;
		if (botaoSelecionado == botaoRetangulo) {
			int xc = Math.min(this.x0, x1);
			int yc = Math.min(this.y0, y1);
			int largura = Math.abs(x1 - this.x0);
			int altura = Math.abs(y1 - this.y0);
			f = new Figura(Figura.RETANGULO, xc, yc, xc + largura, yc + altura);
		} else if (botaoSelecionado == botaoLinha) {
			f = new Figura(Figura.LINHA, this.x0, this.y0, x1, y1);
		} else {
			int xc = Math.min(this.x0, x1);
			int yc = Math.min(this.y0, y1);
			int largura = Math.abs(x1 - this.x0);
			int altura = Math.abs(y1 - this.y0);
			f = new Figura(Figura.ELIPSE, xc, yc, xc + largura, yc + altura);
		}
		figuras.add(f);
		tmp = null;
		areaDeDesenho.repaint();
	}
	
}
