package client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import gui.HeThongQuanLyNhaSach;

public class Client {
	private static HeThongQuanLyNhaSach heThongQuanLyNhaSach;
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		try {
			if (heThongQuanLyNhaSach == null) {
				UIManager.setLookAndFeel(new FlatMacLightLaf());
				heThongQuanLyNhaSach = new HeThongQuanLyNhaSach();
				heThongQuanLyNhaSach.setVisible(true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("Failed to initialize LaF");
		}
	}
}
