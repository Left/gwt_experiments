package gwt.test.linker;

import java.util.SortedSet;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.CompilationResult;
import com.google.gwt.core.ext.linker.LinkerOrder;

@LinkerOrder(LinkerOrder.Order.PRIMARY)
public class MinimalLinker extends AbstractLinker {

	@Override
	public String getDescription() {
		return "Minimal linker";
	}

	@Override
	public ArtifactSet link(TreeLogger logger, LinkerContext context, ArtifactSet artifacts)
			throws UnableToCompleteException {
		SortedSet<CompilationResult> compilations = artifacts.find(CompilationResult.class);
		final ArtifactSet newSet = new ArtifactSet(super.link(logger, context, artifacts));
		
		for (CompilationResult r : compilations) {
			String res = 
					"var $stats;\nvar $wnd = window;\nvar $doc = $wnd.document;" + r.getJavaScript()[0] + "$wnd.addEventListener('load', gwtOnLoad, true);";
			newSet.add(emitString(logger, res, context.getModuleName()+ ".nocache.js"));
		}
		
		return newSet;
	}
}
