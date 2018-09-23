package test;

import com.example.demo.modules.one.ModuleOne;
import com.example.demo.modules.three.ModuleThree;
import com.example.demo.modules.three.SupplierService;
import com.example.demo.modules.two.ModuleTwo;
import com.foreach.across.core.AcrossModule;
import com.foreach.across.core.context.bootstrap.ModuleDependencyMissingException;
import com.foreach.across.test.AcrossTestContext;
import com.foreach.across.test.support.AcrossTestBuilders;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@Slf4j
public class TestModuleBootstrapScenarios
{
	@Test
	public void moduleOneShouldBootstrapInIsolation() {
		try (AcrossTestContext ignore = AcrossTestBuilders.standard( false ).modules( new ModuleOne() ).build()) {
			LOG.trace( "Bootstrap successful." );
		}
	}

	@Test(expected = ModuleDependencyMissingException.class)
	public void moduleTwoRequiresModuleOne() {
		try (AcrossTestContext ignore = AcrossTestBuilders.standard( false )
		                                                  .modules( new ModuleTwo() )
		                                                  .build()) {
			fail( "Should not have bootstrapped." );
		}
	}

	@Test
	public void moduleTwoBootstrapsIfOneIsPresent() {
		try (AcrossTestContext ignore = AcrossTestBuilders.standard( false )
		                                                  .modules( new ModuleTwo(), new ModuleOne() )
		                                                  .build()) {
			LOG.trace( "Bootstrap successful." );
		}
	}

	@Test
	public void supplierServiceFromModuleThreeListsDetectedSuppliersInOrder() {
		expectSuppliers( Collections.emptyList() );
		expectSuppliers( Collections.singletonList( "ExposedComponentOne" ), new ModuleOne() );
		expectSuppliers( Arrays.asList( "ExposedComponentOne", "ExposedComponentTwo" ), new ModuleOne(), new ModuleTwo() );
	}

	@Test
	public void eventIsHandledInModuleOrder() {
		try (AcrossTestContext ctx = AcrossTestBuilders.standard( false )
		                                               .modules( new ModuleThree(), new ModuleOne(), new ModuleTwo() )
		                                               .build()) {
			SupplierService supplierService = ctx.getBeanOfType( SupplierService.class );
			assertEquals( Arrays.asList( "SupplierService", "InternalComponentOne", "InternalComponentTwo" ), supplierService.getEventListeners() );
		}
	}

	private void expectSuppliers( Collection<String> names, AcrossModule... additionalModules ) {
		try (AcrossTestContext ctx = AcrossTestBuilders.standard( false )
		                                               .modules( new ModuleThree() )
		                                               .modules( additionalModules )
		                                               .build()) {
			SupplierService supplierService = ctx.getBeanOfType( SupplierService.class );
			assertEquals( names, supplierService.getSupplierNames() );
		}
	}
}
