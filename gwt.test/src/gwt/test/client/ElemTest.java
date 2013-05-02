/**
 * 
 */
package gwt.test.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

import elemental.client.*;
import elemental.dom.*;
import elemental.events.Event;
import elemental.events.EventListener;
import elemental.events.MouseEvent;
import elemental.html.*;
import elemental.js.util.JsArrayOfInt;
import elemental.js.util.JsArrayOfNumber;
import elemental.js.util.JsElementalBase;
import elemental.js.util.JsMapFromStringToBoolean;
import elemental.js.util.JsMapFromStringToString;
import elemental.js.util.JsMappable;
import elemental.util.Mappable;

/**
 * @author Left
 *
 */
public class ElemTest implements EntryPoint {
	CanvasElement canvasElement;
	float fovy = 1.f;
	boolean leftButtonDown = false;
	
	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public static native Mappable badExample() /*-{
	  return { audio: true, video: false };
	}-*/;
	
	@Override
	public void onModuleLoad() {
		final Window window = Browser.getWindow();
		window.getConsole().log("Bla-bla-bla");
/*		
	    window.getNavigator().webkitGetUserMedia(badExample(), new NavigatorUserMediaSuccessCallback() {
			@Override
			public boolean onNavigatorUserMediaSuccessCallback(LocalMediaStream stream) {
				window.getConsole().log("!!!!!!!!!!!");
				
				Mappable mic = createMediaStreamSource(stream); 
				window.getConsole().log(mic);
					
				MediaStreamTrackList audioTracks = stream.getAudioTracks();
				for (int track = 0; track < audioTracks.getLength(); ++track) {
					MediaStreamTrack item = audioTracks.item(track);
					window.getConsole().log(item.getKind() + "," + item.getLabel() + "," + item.isEnabled());
				}
				
				return true;
			}
		},
		new NavigatorUserMediaErrorCallback() {
			@Override
			public boolean onNavigatorUserMediaErrorCallback(
					NavigatorUserMediaError error) {
				window.getConsole().log("ERROR");
				return true;
			}
		});
*/
		window.addEventListener("load", new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				window.getConsole().log("load");
			}
		}, false);
			
		window.addEventListener("resize", new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				window.getConsole().log("resize");
				
				redraw(window);
			}
		}, false);

		final Document document = window.getDocument();
		canvasElement = document.createCanvasElement();
		Element body = document.getBody();
		body.appendChild(canvasElement);
		body.getStyle().setMargin(0, "px");
		body.getStyle().setOverflow("hidden");

		window.setTimeout(new TimeoutHandler() {
			@Override
			public void onTimeoutHandler() {
				canvasElement.addEventListener("mousemove", new EventListener() {
					@Override
					public void handleEvent(Event evt) {
						MouseEvent me = (MouseEvent) evt;
						if (leftButtonDown) {
							if (canvasElement.getOffsetWidth()/2. < me.getOffsetX()) {
								fovy *= 1.03f;
							} else {
								fovy /= 1.03f;
							}
							redraw(window);
							// window.getConsole().log("mousemove:" + me.getOffsetX() + "," + me.getOffsetY());
						}
					}
				}, false);
				canvasElement.addEventListener("mousedown", new EventListener() {
					@Override
					public void handleEvent(Event evt) {
						MouseEvent me = (MouseEvent) evt;
						if(me.getWhich() == 1) {
							leftButtonDown = true;
						}
					}
				}, false);
				canvasElement.addEventListener("mouseup", new EventListener() {
					@Override
					public void handleEvent(Event evt) {
						MouseEvent me = (MouseEvent) evt;
						if(me.getWhich() == 1) {
							leftButtonDown = false;
						}
					}
				}, false);

			}
		}, 1000);

		canvasElement.addEventListener("click", new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				MouseEvent me = (MouseEvent) evt;
				window.getConsole().log("Click:" + me.getOffsetX() + "," + me.getOffsetY());				
			}
		}, false);

		redraw(window);
		/*
		AudioContext audioContext = window.newAudioContext();
		Oscillator osc = audioContext.createOscillator();
		osc.setType(Oscillator.SINE);
		osc.connect((AudioParam) audioContext.getDestination(), 0);
		osc.getFrequency().setValue(220.0f);
		osc.noteOn(0);
		*/
	}
	
	private void redraw(final Window window) {
        long time = getTimeMs();

		int w = window.getInnerWidth();
		int h = window.getInnerHeight();

		canvasElement.setWidth(w);
		canvasElement.setHeight(h);

		WebGLRenderingContext ctx3d = (WebGLRenderingContext) canvasElement.getContext("experimental-webgl");
		ctx3d.viewport(0, 0, 640, 480);

		ctx3d.enable(WebGLRenderingContext.DEPTH_TEST);
		ctx3d.depthFunc(WebGLRenderingContext.LEQUAL); 

		WebGLShader vs  = 
			createShader(WebGLRenderingContext.VERTEX_SHADER,    
					Shaders.INSTANCE.vertexShader().getText(), ctx3d);
		WebGLShader fs = 
			createShader(WebGLRenderingContext.FRAGMENT_SHADER, 
					Shaders.INSTANCE.fragmentShader().getText(), ctx3d);
		WebGLProgram program = 
			createAndUseProgram(Arrays.asList(vs,fs), ctx3d);


		WebGLUniformLocation pMatrixUniform = 
			ctx3d.getUniformLocation(program, "uPMatrix");
		WebGLUniformLocation mvMatrixUniform = 
			ctx3d.getUniformLocation(program, "uMVMatrix");
		WebGLUniformLocation  nmMatrixUniform  = 
			ctx3d.getUniformLocation(program, "uNormalMatrix");
		WebGLUniformLocation colorUniform = 
			ctx3d.getUniformLocation(program, "uColor");

		WebGLUniformLocation ambientColorUniform = 
			ctx3d.getUniformLocation(program, "uAmbientLight");
		WebGLUniformLocation lightColorUniform = 
			ctx3d.getUniformLocation(program, "uLightColor");
		WebGLUniformLocation lightDirectionUniform = 
			ctx3d.getUniformLocation(program, "uLightDirection");

		int vertexPositionAttribute = 
			ctx3d.getAttribLocation(program, "aVertexPosition");  
		int vertexNormalAttribute = 
			ctx3d.getAttribLocation(program, "aVertexNormal"); 

		WebGLCube cube = new WebGLCube();

		WebGLBuffer verticesPositionsBuffer = ctx3d.createBuffer();
		ctx3d.bindBuffer(
				WebGLRenderingContext.ARRAY_BUFFER, 
				verticesPositionsBuffer);
		ctx3d.bufferData(WebGLRenderingContext.ARRAY_BUFFER, 
				createFloat32Array(cube.getVerticesArray()),  
				WebGLRenderingContext.STATIC_DRAW);

		ctx3d.vertexAttribPointer(vertexPositionAttribute, 3,
				WebGLRenderingContext.FLOAT, false, 0, 0);
		ctx3d.enableVertexAttribArray(vertexPositionAttribute);

		WebGLBuffer verticesNormalsBuffer = ctx3d.createBuffer();
		ctx3d.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, 
				verticesNormalsBuffer);
		ctx3d.bufferData(WebGLRenderingContext.ARRAY_BUFFER, 
				createFloat32Array(cube.getNormalsArray()),  
				WebGLRenderingContext.STATIC_DRAW);		
		ctx3d.vertexAttribPointer(vertexNormalAttribute, 3,
				WebGLRenderingContext.FLOAT, false, 0, 0);        
		ctx3d.enableVertexAttribArray(vertexNormalAttribute);

		WebGLBuffer indexesBuffer = ctx3d.createBuffer();   
		ctx3d.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, 
				indexesBuffer);
		ctx3d.bufferData(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, 
				createUint16Array(cube.getIndexesArray()), 
				WebGLRenderingContext.STATIC_DRAW);

		int numIndicies = cube.getNumIndices();

		double[] perspectiveMatrix = perspectiveMatrix(40., 640./480., 0.1, 1000.);

		double[] modelViewMatrix = lookaAtMatrix(new double[] {3.5*fovy, 3.5*fovy, 4.0*fovy} , new double[]{0,0,1},new double[]{0,0,1});

		ctx3d.uniformMatrix4fv(pMatrixUniform, false,
				createFloat32Array(perspectiveMatrix));

		ctx3d.uniformMatrix4fv(mvMatrixUniform, false,
				createFloat32Array(modelViewMatrix));

		double[] normalTransformMatrix = transpose(inverseMatrix(modelViewMatrix));

		ctx3d.uniformMatrix4fv(nmMatrixUniform, false,
				createFloat32Array(normalTransformMatrix));

		ctx3d.uniform4f(colorUniform, .0f, .5f, .8f, 1f);
		ctx3d.uniform3f(ambientColorUniform, .2f, .2f, .2f);
		ctx3d.uniform3f(lightColorUniform, 0.8f, 0.8f, 0.8f);
		ctx3d.uniform3f(lightDirectionUniform, 0.0f, 
				1.0f/(float)Math.sqrt(2.0),
				1.0f/(float)Math.sqrt(2.0));

		//not needed because indexesBuffer is the currently binded ELEMENT_ARRAY_BUFFER
		//		ctx3d.bindBuffer(
		//				WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, 
		//				indexesBuffer);

		ctx3d.clear(
				WebGLRenderingContext.COLOR_BUFFER_BIT |
				WebGLRenderingContext.DEPTH_BUFFER_BIT);     

		ctx3d.drawElements(WebGLRenderingContext.TRIANGLES, 
				numIndicies,
				WebGLRenderingContext.UNSIGNED_SHORT, 0);

	}
	
	public static Float32Array createFloat32Array(double ... nums) {
		JsArrayOfNumber vertices = JsArrayOfNumber.create();
		for(double d: nums) 
			vertices.push(d);
		return  createFloat32Array(vertices);

	}

	private native static Float32Array createFloat32Array(JsArrayOfNumber vertices) /*-{
		return new Float32Array(vertices);
	}-*/;


	public static Uint16Array createUint16Array(int ... nums) {
		JsArrayOfInt is = JsArrayOfInt.create();
		for(int i: nums) 
			is.push(i);
		return  createUint16Array(is);
	}


	private native static Uint16Array createUint16Array(JsArrayOfInt vertices) /*-{
		return new Uint16Array(vertices);
	}-*/;

	public class WebGLCube {
		//data
		private  final double[] vertices = { 
				// Front face
				-1.0, -1.0,  2.0,
				1.0,  -1.0,  2.0,
				1.0,   1.0,  2.0,
				-1.0,  1.0,  2.0,

				// Back face
				-1.0, -1.0, -0.0,
				-1.0,  1.0, -0.0,
				1.0,   1.0, -0.0,
				1.0,  -1.0, -0.0,

				// Top face
				-1.0,  1.0, -0.0,
				-1.0,  1.0,  2.0,
				1.0,   1.0,  2.0,
				1.0,   1.0, -0.0,

				// Bottom face
				-1.0,  -1.0, -0.0,
				1.0,   -1.0, -0.0,
				1.0,   -1.0,  2.0,
				-1.0,  -1.0,  2.0,

				// Right face
				1.0,  -1.0, -0.0,
				1.0,   1.0, -0.0,
				1.0,   1.0,  2.0,
				1.0,  -1.0,  2.0,

				// Left face
				-1.0,  -1.0, -0.0,
				-1.0,  -1.0,  2.0,
				-1.0,   1.0,  2.0,
				-1.0,   1.0, -0.0
		};

		private  final double[] normals = { 
				// Front face
				0.0, 0.0,  1.0,
				0.0, 0.0,  1.0,
				0.0, 0.0,  1.0,
				0.0, 0.0,  1.0,

				// Back face
				0.0, 0.0, -1.0,
				0.0, 0.0, -1.0,
				0.0, 0.0, -1.0,
				0.0, 0.0, -1.0,

				// Top face
				0.0,  1.0, 0.0,
				0.0,  1.0, 0.0,
				0.0,  1.0, 0.0,
				0.0,  1.0, 0.0,

				// Bottom face
				0.0,  -1.0, 0.0,
				0.0,  -1.0, 0.0,
				0.0,  -1.0, 0.0,
				0.0,  -1.0, 0.0,

				// Right face
				1.0,  0.0, 0.0,
				1.0,  0.0, 0.0,
				1.0,  0.0, 0.0,
				1.0,  0.0, 0.0,

				// Left face
				-1.0,  0.0, 0.0,
				-1.0,  0.0, 0.0,
				-1.0,  0.0, 0.0,
				-1.0,  0.0, 0.0
		};

		private  final int[] triangles = {
				0, 1, 2,      0, 2, 3,    // Front face
				4, 5, 6,      4, 6, 7,    // Back face
				8, 9, 10,     8, 10, 11,  // Top face
				12, 13, 14,   12, 14, 15, // Bottom face
				16, 17, 18,   16, 18, 19, // Right face
				20, 21, 22,   20, 22, 23  // Left face
				//
				//
		};

		private  final double[] texture_coords = {
				////
				// Front face
				0.0, 0.0,
				1.0, 0.0,
				1.0, 1.0,
				0.0, 1.0,

				// Back face
				1.0, 0.0,
				1.0, 1.0,
				0.0, 1.0,
				0.0, 0.0,

				// Top face
				0.0, 1.0,
				0.0, 0.0,
				1.0, 0.0,
				1.0, 1.0,

				// Bottom face
				1.0, 1.0,
				0.0, 1.0,
				0.0, 0.0,
				1.0, 0.0,

				// Right face
				1.0, 0.0,
				1.0, 1.0,
				0.0, 1.0,
				0.0, 0.0,

				// Left face
				0.0, 0.0,
				1.0, 0.0,
				1.0, 1.0,
				0.0, 1.0
		};


		private  native JsArrayOfNumber fromDoubleArray(double[] a) /*-{
			return a;
		}-*/;

		private  native JsArrayOfInt fromIntArray(int[] a) /*-{
			return a;
		}-*/;

		public JsArrayOfNumber getVerticesArray() {
			return fromDoubleArray(vertices);
		}

		public JsArrayOfInt getIndexesArray() {
			return fromIntArray(triangles);
		}

		public int getNumIndices() {
			return triangles.length;
		}

		public JsArrayOfNumber getTextureCoordinatesArray() {
			return fromDoubleArray(texture_coords);
		}

		public JsArrayOfNumber getNormalsArray() {
			return fromDoubleArray(normals);
		}

	}

	private long getTimeMs() {
		return (new Date()).getTime();
	}
	
	public interface Shaders extends ClientBundle {
		public static Shaders INSTANCE = GWT.create(Shaders.class);
		@Source("vertexShader.txt")
		public TextResource vertexShader();
		@Source("fragmentShader.txt")
		public TextResource fragmentShader();
	}

	public static  WebGLShader createShader(int type,String code,WebGLRenderingContext ctx) {
		WebGLShader shader = ctx.createShader(type);
		ctx.shaderSource(shader,code );
		ctx.compileShader(shader);
		testShaderStatus(ctx,shader);
		return shader;
	}

	public static WebGLProgram createAndUseProgram(List<WebGLShader> shaders, WebGLRenderingContext ctx) {
		WebGLProgram program = ctx.createProgram();

		for(WebGLShader s : shaders)
			ctx.attachShader(program, s);

		ctx.linkProgram(program);
		testProgramStatus(ctx,program);

		ctx.useProgram(program);
		return program;
	}

	private static native boolean testShaderStatus(WebGLRenderingContext gl, WebGLShader shader) /*-{

	// Check the compile status
	  var compiled = gl.getShaderParameter(shader, gl.COMPILE_STATUS);
	  if (!compiled) {
	    // Something went wrong during compilation; get the error
	    lastError = gl.getShaderInfoLog(shader);
	    alert("*** Error compiling shader '" + shader + "':" + lastError);
	    gl.deleteShader(shader);
	    return false;
	  }
	//alert("shader ok");

	  return true;
	}-*/;


	private static native boolean testProgramStatus(WebGLRenderingContext gl, WebGLProgram program) /*-{
	  // Check the link status
	  var linked = gl.getProgramParameter(program, gl.LINK_STATUS);
	  if (!linked) {
	      // something went wrong with the link
	      lastError = gl.getProgramInfoLog (program);
	      alert("Error in program linking:" + lastError);
	
	      gl.deleteProgram(program);
	      return false;
	  }
	 //alert("program ok");
	  return true;
	}-*/;

	//QnD js->java from from https://github.com/toji/gl-matrix
	/*
	 * double[] perspective
	 * Generates a perspective projection matrix with the given bounds
	 *
	 * Params:
	 * fovy - scalar, vertical field of view
	 * aspect - scalar, aspect ratio. typically viewport width/height
	 * near, far - scalar, near and far bounds of the frustum
	 * dest - Optional, mat4 frustum matrix will be written into
	 *
	 * Returns:
	 * dest if specified, a new mat4 otherwise
	 */
	public static double[] 
	                     perspectiveMatrix(double fovy, double aspect, 
	                    		 double near, double far) {
		double top = near*Math.tan(fovy*Math.PI / 360.0);
		double right = top*aspect;
		return frustumMatrix(-right, right, -top, top, near, far);
	};

	/*
	 * double[] frustum
	 * Generates a frustum matrix with the given bounds
	 *
	 * Params:
	 * left, right - scalar, left and right bounds of the frustum
	 * bottom, top - scalar, bottom and top bounds of the frustum
	 * near, far - scalar, near and far bounds of the frustum
	 * dest - Optional, mat4 frustum matrix will be written into
	 *
	 * Returns:
	 * dest if specified, a new mat4 otherwise
	 */
	public static double[] 
	                     frustumMatrix(double left, double right, 
	                    		 double bottom, double top, 
	                    		 double near, double far) {

		double dest[] = new double[16];
		double rl = (right - left);
		double tb = (top - bottom);
		double fn = (far - near);
		dest[0] = (near*2.0) / rl;
		dest[1] = 0.0;
		dest[2] = 0.0;
		dest[3] = 0.0;
		dest[4] = 0.0;
		dest[5] = (near*2.0) / tb;
		dest[6] = 0.0;
		dest[7] = 0.0;
		dest[8] = (right + left) / rl;
		dest[9] = (top + bottom) / tb;
		dest[10] = -(far + near) / fn;
		dest[11] = -1.0;
		dest[12] = 0.0;
		dest[13] = 0.0;
		dest[14] = -(far*near*2.0) / fn;
		dest[15] = 0.0;
		return dest;
	}



	/*
	 * double[] lookAt
	 * Generates a look-at matrix with the given eye position, focal point, and up axis
	 *
	 * Params:
	 * eye - vec3, position of the viewer
	 * center - vec3, point the viewer is looking at
	 * up - vec3 pointing "up"
	 * dest - Optional, mat4 frustum matrix will be written into
	 *
	 * Returns:
	 * dest if specified, a new mat4 otherwise
	 */

	public static  double[] lookaAtMatrix(double[] eye, double[] center, double[] up) {
		double eyex = eye[0];
		double eyey = eye[1];
		double eyez = eye[2];
		double upx = up[0];
		double upy = up[1];
		double upz = up[2];

		double z0,z1,z2,x0,x1,x2,y0,y1,y2,len;

		//vec3.direction(eye, center, z);
		z0 = eyex - center[0];
		z1 = eyey - center[1];
		z2 = eyez - center[2];

		// normalize (no check needed for 0 because of early return)
		len = 1.0/Math.sqrt(z0*z0 + z1*z1 + z2*z2);
		z0 *= len;
		z1 *= len;
		z2 *= len;

		//vec3.normalize(vec3.cross(up, z, x));
		x0 = upy*z2 - upz*z1;
		x1 = upz*z0 - upx*z2;
		x2 = upx*z1 - upy*z0;
		len = Math.sqrt(x0*x0 + x1*x1 + x2*x2);
		if (len==0) {
			x0 = 0;
			x1 = 0;
			x2 = 0;
		} else {
			len = 1.0/len;
			x0 *= len;
			x1 *= len;
			x2 *= len;
		};

		//vec3.normalize(vec3.cross(z, x, y));
		y0 = z1*x2 - z2*x1;
		y1 = z2*x0 - z0*x2;
		y2 = z0*x1 - z1*x0;

		len = Math.sqrt(y0*y0 + y1*y1 + y2*y2);
		if (len==0) {
			y0 = 0;
			y1 = 0;
			y2 = 0;
		} else {
			len = 1.0/len;
			y0 *= len;
			y1 *= len;
			y2 *= len;
		}

		double[] dest = new double[16];
		dest[0] = x0;
		dest[1] = y0;
		dest[2] = z0;
		dest[3] = 0.0;
		dest[4] = x1;
		dest[5] = y1;
		dest[6] = z1;
		dest[7] = 0.0;
		dest[8] = x2;
		dest[9] = y2;
		dest[10] = z2;
		dest[11] = 0.0;
		dest[12] = -(x0*eyex + x1*eyey + x2*eyez);
		dest[13] = -(y0*eyex + y1*eyey + y2*eyez);
		dest[14] = -(z0*eyex + z1*eyey + z2*eyez);
		dest[15] = 1.0;

		return dest;
	}

	/*
	 * double[] inverse
	 * Calculates the inverse matrix of a mat4
	 *
	 * Params:
	 * mat - mat4 to calculate inverse of
	 * dest - Optional, mat4 receiving inverse matrix. If not specified result is written to mat
	 *
	 * Returns:
	 * dest is specified, mat otherwise
	 */
	public static double[] inverseMatrix(double[] mat) {

		double[] dest = new double[16];

		// Cache the matrix values (makes for huge speed increases!)
		double a00 = mat[0], a01 = mat[1], a02 = mat[2], a03 = mat[3];
		double a10 = mat[4], a11 = mat[5], a12 = mat[6], a13 = mat[7];
		double a20 = mat[8], a21 = mat[9], a22 = mat[10], a23 = mat[11];
		double a30 = mat[12], a31 = mat[13], a32 = mat[14], a33 = mat[15];

		double b00 = a00*a11 - a01*a10;
		double b01 = a00*a12 - a02*a10;
		double b02 = a00*a13 - a03*a10;
		double b03 = a01*a12 - a02*a11;
		double b04 = a01*a13 - a03*a11;
		double b05 = a02*a13 - a03*a12;
		double b06 = a20*a31 - a21*a30;
		double b07 = a20*a32 - a22*a30;
		double b08 = a20*a33 - a23*a30;
		double b09 = a21*a32 - a22*a31;
		double b10 = a21*a33 - a23*a31;
		double b11 = a22*a33 - a23*a32;

		// Calculate the determinant (inlined to avoid double-caching)
		double invDet = 1.0/(b00*b11 - b01*b10 + b02*b09 + b03*b08 - b04*b07 + b05*b06);

		dest[0] = (a11*b11 - a12*b10 + a13*b09)*invDet;
		dest[1] = (-a01*b11 + a02*b10 - a03*b09)*invDet;
		dest[2] = (a31*b05 - a32*b04 + a33*b03)*invDet;
		dest[3] = (-a21*b05 + a22*b04 - a23*b03)*invDet;
		dest[4] = (-a10*b11 + a12*b08 - a13*b07)*invDet;
		dest[5] = (a00*b11 - a02*b08 + a03*b07)*invDet;
		dest[6] = (-a30*b05 + a32*b02 - a33*b01)*invDet;
		dest[7] = (a20*b05 - a22*b02 + a23*b01)*invDet;
		dest[8] = (a10*b10 - a11*b08 + a13*b06)*invDet;
		dest[9] = (-a00*b10 + a01*b08 - a03*b06)*invDet;
		dest[10] = (a30*b04 - a31*b02 + a33*b00)*invDet;
		dest[11] = (-a20*b04 + a21*b02 - a23*b00)*invDet;
		dest[12] = (-a10*b09 + a11*b07 - a12*b06)*invDet;
		dest[13] = (a00*b09 - a01*b07 + a02*b06)*invDet;
		dest[14] = (-a30*b03 + a31*b01 - a32*b00)*invDet;
		dest[15] = (a20*b03 - a21*b01 + a22*b00)*invDet;

		return dest;
	}

	/*
	 * double[] transpose
	 * Transposes a mat4 (flips the values over the diagonal)
	 *
	 * Params:
	 * mat - mat4 to transpose
	 * dest - Optional, mat4 receiving transposed values. If not specified result is written to mat
	 *
	 * Returns:
	 * dest is specified, mat otherwise
	 */
	public static double[] transpose(double[] mat) {
		double[]	dest = new double[16]; 
		dest[0] = mat[0];
		dest[1] = mat[4];
		dest[2] = mat[8];
		dest[3] = mat[12];
		dest[4] = mat[1];
		dest[5] = mat[5];
		dest[6] = mat[9];
		dest[7] = mat[13];
		dest[8] = mat[2];
		dest[9] = mat[6];
		dest[10] = mat[10];
		dest[11] = mat[14];
		dest[12] = mat[3];
		dest[13] = mat[7];
		dest[14] = mat[11];
		dest[15] = mat[15];
		return dest;
	};

}
