package Controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Graph;
import model.Vertex;
import view.ArrowHead;
import view.PaintPanel;

public class PaintListener implements MouseListener {
	private Graph graph;
	private Vertex vertex;
	private PaintPanel paintPanel;
	private int index = 1;
	private Font font;

	public PaintListener(Graph graph, PaintPanel paintPanel) {
		super();
		this.graph = graph;
		this.paintPanel = paintPanel;
		this.font = new Font("Arial", font.BOLD, 15);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (paintPanel.getTypeButtonString()) {
		case "addVertex": {
			Graphics g = paintPanel.getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2.setColor(Color.black);
			Ellipse2D el = new Ellipse2D.Double((int) e.getX(), (int) e.getY(), 50, 50);
			graph.addVertex(el);
			g2.fill(el);

			FontMetrics metrics = g.getFontMetrics(font);
			g.setFont(font);
			g.setColor(Color.white);
			String string = index + "";
			int x = (int) (el.getX() + (el.getWidth() - metrics.stringWidth(string)) / 2);
			int y = (int) (el.getY() + (el.getHeight() - metrics.getHeight()) / 2) + 14;
			g.drawString(string, x, y);

			index++;
			paintPanel.setTypeButtonString("");
		}
		case "addEdge": {
			for (int i = 0; i < graph.getVertexs().size(); i++) {
				if (graph.getVertexs().get(i).getEllipse2d().contains(e.getX(), e.getY())) {
					System.out.println("started");
					if (paintPanel.getSelected1() == null) {
						paintPanel.setSelected1(graph.getVertexs().get(i));
						System.out.println("selected1 available");
						return;
					} else {
						if (paintPanel.getSelected1() != paintPanel.getSelected2()) {
							paintPanel.setSelected2(graph.getVertexs().get(i));
							System.out.println("selected2 available");
							if (paintPanel.isUndirecred() == true) {
								graph.addUnderectedEdge(paintPanel.getSelected1(), paintPanel.getSelected2());
								Graphics graphics = paintPanel.getGraphics();
								Graphics2D graphics2d = (Graphics2D) graphics;
								graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
										RenderingHints.VALUE_ANTIALIAS_ON);
								graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING,
										RenderingHints.VALUE_RENDER_QUALITY);
								graphics2d.setStroke(new BasicStroke(7f));
//								graphics2d.draw(new Line2D.Double(paintPanel.getSelected1().getEllipse2d().getCenterX(),
//										paintPanel.getSelected1().getEllipse2d().getCenterY(),
//										paintPanel.getSelected2().getEllipse2d().getCenterX(),
//										paintPanel.getSelected2().getEllipse2d().getCenterY()));

								double from = paintPanel.angleBetween(paintPanel.getSelected1(),
										paintPanel.getSelected2());
								double to = paintPanel.angleBetween(paintPanel.getSelected1(),
										paintPanel.getSelected2());

								Point2D pointFromPoint2d = paintPanel.getPointOnCircle(paintPanel.getSelected1(), from);
								Point2D pointToPoint2d = paintPanel.getPointOnCircle(paintPanel.getSelected2(),
										to - 22);

								graphics2d.draw(new Line2D.Double(pointFromPoint2d, pointToPoint2d));
								graphics2d.dispose();
								paintPanel.setSelected1(null);
								paintPanel.setSelected2(null);
								paintPanel.setTypeButtonString("");
								System.out.println("Edge is available");
								break;
							}
							if (paintPanel.isDirected() == true) {
								graph.addDerectedEdge(paintPanel.getSelected1(), paintPanel.getSelected2());
								Graphics graphics = paintPanel.getGraphics();
								Graphics2D graphics2d = (Graphics2D) graphics;
								graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
										RenderingHints.VALUE_ANTIALIAS_ON);
								graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING,
										RenderingHints.VALUE_RENDER_QUALITY);
								graphics2d.setStroke(new BasicStroke(7f));
//								graphics2d.draw(new Line2D.Double(paintPanel.getSelected1().getEllipse2d().getCenterX(),
//										paintPanel.getSelected1().getEllipse2d().getCenterY(),
//										paintPanel.getSelected2().getEllipse2d().getCenterX(),
//										paintPanel.getSelected2().getEllipse2d().getCenterY()));

								double from = paintPanel.angleBetween(paintPanel.getSelected1(),
										paintPanel.getSelected2());
								double to = paintPanel.angleBetween(paintPanel.getSelected1(),
										paintPanel.getSelected2());

								Point2D pointFromPoint2d = paintPanel.getPointOnCircle(paintPanel.getSelected1(), from);
								Point2D pointToPoint2d = paintPanel.getPointOnCircle(paintPanel.getSelected2(),
										to - 22);

								graphics2d.draw(new Line2D.Double(pointFromPoint2d, pointToPoint2d));
								graphics2d.setStroke(new BasicStroke(4f));
								ArrowHead arrowHead = new ArrowHead();
								AffineTransform affineTransform = AffineTransform.getTranslateInstance(
										pointToPoint2d.getX() - (arrowHead.getBounds().getWidth() / 2d),
										pointToPoint2d.getY());
								affineTransform.rotate(from, arrowHead.getBounds2D().getCenterX(), 0);
								arrowHead.transform(affineTransform);
								graphics2d.draw(arrowHead);
								graphics2d.dispose();
								paintPanel.setSelected1(null);
								paintPanel.setSelected2(null);
								paintPanel.setTypeButtonString("");
								System.out.println("Edge is available");
								break;
							}

							if (paintPanel.isDirected() == false && paintPanel.isUndirecred() == false
									&& paintPanel.getSelected1() != null && paintPanel.getSelected2() != null) {
								JOptionPane.showMessageDialog(paintPanel, "Type of Edge is not Check!!!", "Error",
										JOptionPane.ERROR_MESSAGE);
								System.out.println("Type of Edge is not Check!!!");
								paintPanel.setSelected1(null);
								break;
							}
						}
					}
				}
			}
		}
		case "delVertex": {
			
		}
		case "":
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
