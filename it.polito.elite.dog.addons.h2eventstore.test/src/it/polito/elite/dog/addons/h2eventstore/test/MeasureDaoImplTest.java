package it.polito.elite.dog.addons.h2eventstore.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import it.polito.elite.dog.addons.h2eventstore.dao.MeasureDao;
import it.polito.elite.dog.addons.h2eventstore.dao.MeasureDaoImp;

import javax.measure.Measure;
import javax.measure.unit.Unit;

import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;

public class MeasureDaoImplTest {

	private MeasureDao dao = null;

	@Before
	public void setup() throws SQLException {
		final BundleContext context = org.osgi.framework.FrameworkUtil.getBundle(getClass()).getBundleContext();
		dao = new MeasureDaoImp("jdbc:h2:~/test", "sa", "", context);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testInsertLoop() {

		for (int i = 0; i < 100; i++) {
			
			try {
				dao.insert("name", Measure.valueOf(new BigDecimal(200), Unit.valueOf("A")), new Date());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
