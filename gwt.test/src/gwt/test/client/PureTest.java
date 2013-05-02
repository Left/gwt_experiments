/**
 * 
 */
package gwt.test.client;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;


/**
 * @author Left
 *
 */
public class PureTest implements EntryPoint {
	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public static native JavaScriptObject window() /*-{
	  return $wnd;
	}-*/;
	
	public static native void log(Object o) /*-{
	  return console.log(o);
	}-*/;
	
	public static native void runMe(Function<JsArrayNumber, Void> fn) /*-{
	  var context = new window.webkitAudioContext();
	  $wnd['context'] = context;
	  navigator.webkitGetUserMedia({audio:true},
	    function(stream) {
	    	var microphone = context.createMediaStreamSource(stream);
	    	// var peer = context.createScriptProcessor(4096, 1, 1);
	    	
	    	var peer = context.createJavaScriptNode(4096, 1, 1);
			microphone.connect(peer);
			peer.connect(context.destination);

	  		peer.onaudioprocess = function (e) {
	  			var buffer = e.inputBuffer.getChannelData(0);

		        var maxVal = 0;
		        // Iterate through buffer to check if any of the |values| exceeds 1.
		        for (var i = 0; i < buffer.length; i++) {
		            if (maxVal < buffer[i]) {
		                maxVal = buffer[i];
		            }
		        }
		        if(maxVal <= 0.001){
		            console.log(0.0);
		        } else if(maxVal > 1){
		            console.log(1);
		        } else if(maxVal > 0.2){
		            console.log(0.2);
		        } else if(maxVal > 0.1){
		            console.log(0.1);
		        } else if(maxVal > 0.05){
		            console.log(0.05);
		        } else if(maxVal > 0.025){
		            console.log(0.025);
		        } else if(maxVal > 0.01){
		            console.log(0.01);
		        }
        
	  			fn.@com.google.common.base.Function::apply(*)(e.inputBuffer.getChannelData(0));
			};

	  		console.log(peer);
	  		console.log(stream);
	  		console.log(stream.getAudioTracks()[0]);
	  		console.log(context);
	    },
	  	function(e) {console.log(e);} ); 
	}-*/;

	public static native void docW(Object o) /*-{
	  return document.write(o);
	}-*/;
	/*
	public static void delay(final Runnable r, int timeout) {
		Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
			@Override
			public boolean execute() {
				r.run();
				return false;
			}
		}, timeout);
	};
	*/
	
	static class Point {
		int x;
		int y;
	}
	
	@Override
	public void onModuleLoad() {
		log("hello there");
		/*
		final Random r = new Random();
		final PrimesIterator it = new PrimesIterator();

		final int i[] = {0};
		delay(new Runnable() {
			@Override
			public void run() {
				List<Integer> res = Lists.newArrayList();
				for (int j=0; j<1000; ++j) {
					i[0]++;
					if (it.isPrime(i[0])) {
						res.add(i[0]);
					}
				}
				if (res.size() > 0) {
					docW("<div>" + Joiner.on(",").join(res) + "</div>");
				}

				// delay(this, 0);
			}
		}, 0);
		*/
		
		final long[] total = {0};
		
		runMe(new Function<JsArrayNumber, Void>() {
			@Override
			@Nullable
			public Void apply(@Nullable JsArrayNumber arg0) {
				String res = "";
				for (int i=0; i<arg0.length(); ++i, ++total[0]) {
					if (arg0.get(i) != 0) {
						res += arg0.get(i);
					}
				}
				
				docW("<div>" + res + "</div>");
				return null;
			}			
		});
	}

}
