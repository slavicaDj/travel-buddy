package listeners;

import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;

public class PrivilegeListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final HashMap<String, Integer> accessReq;

	static {
		accessReq = new HashMap<String, Integer>();
		
		// guest privilege
		accessReq.put("/index.xhtml", 1);
		accessReq.put("/registracija.xhtml", 1);
		
		// user privilege
		accessReq.put("/home.xhtml",2);
		accessReq.put("/objavi_oglas.xhtml", 2);
		accessReq.put("/oglas_detaljno.xhtml", 2);
		accessReq.put("/posalji_poruku.xhtml",2);
		accessReq.put("/moje_poruke.xhtml",2);
		accessReq.put("/moji_oglasi.xhtml",2);
		accessReq.put("/azuriraj_oglas.xhtml",2);
		accessReq.put("/poruke_detaljno.xhtml",2);
		accessReq.put("/prijavi_sadrzaj.xhtml",2);
		accessReq.put("/kupi_kartu.xhtml",2);

		//admin privilegie
		accessReq.put("/odobravanje_naloga.xhtml", 3);
		accessReq.put("/prijave.xhtml", 3);
		accessReq.put("/izvjestaji.xhtml", 3);

	}

	@Override
	public void afterPhase(PhaseEvent arg0) {
		
		UserBean korisnikBean = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("korisnikBean");
		FacesContext ctx = arg0.getFacesContext();
		HttpServletResponse resp = (HttpServletResponse) ctx.getExternalContext().getResponse();
		int type = 0;
		boolean redirect = false;
		
		if (korisnikBean != null && korisnikBean.isLoggedIn()) {
			type = korisnikBean.getKorisnik().isAdmin()? 3 : 2;
		}
		else {
			type = 1;
		}
				
		String page = arg0.getFacesContext().getViewRoot().getViewId();
		
		if ("/index.xhtml".equals(page)) {
			return;
		}
		
		if (!accessReq.containsKey(page)) {
			redirect = true;
		} 
		else if (accessReq.get(page) > type) {
			redirect = true;
		}
		else {
			if (((type == 2)  || (type == 3)) && ("/registracija.xhtml".equals(page))) {
				redirect = true;
			}
		}
		
		if (redirect == true) {
			try {
				if (!resp.isCommitted()) {
					ctx.getExternalContext().redirect("index.xhtml");
				}
			} 
			catch (Exception e) {
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {

	}

	@Override
	public PhaseId getPhaseId() {
		//return PhaseId.RESTORE_VIEW;
		return PhaseId.RENDER_RESPONSE;
	}

}
