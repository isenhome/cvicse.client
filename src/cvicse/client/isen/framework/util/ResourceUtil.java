package cvicse.client.isen.framework.util;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Environment;
import cvicse.client.isen.framework.logging.BysLogger;

/**
 * Created by IntelliJ IDEA.
 * User: yanshan
 * Date: 2011-1-18
 * Time: 16:19:12
 */
public final class ResourceUtil {
	private static BysLogger log = BysLogger.getLogger();

    private static final int BUFFER = 4096;

    private ResourceUtil() {
    }

    /**
     * Open file from assets folder from .apk
     *
     * @param context Context Activity ok
     * @param path    file path in assets, relative path, must don't / start.
     * @return InputStream for read
     * @throws IOException IOE
     */
    public static InputStream openAsserts(Context context, String path) throws IOException {
        InputStream in = null;
        if (context != null && !StringUtil.isBlank(path)) {
            char ch = path.charAt(0);
            // In android os, open asserts path must not / startsWith
            if (ch == '/') {
                path = path.substring(1);
            }
            AssetManager assets = context.getAssets();
            in = assets.open(path);
        }
        return in;
    }

    /**
     * Open assets resources from .apk
     *
     * @param context
     * @param path path could not start with /
     * @return InputStream
     * @throws IOException
     */
    public static InputStream readAsserts(Context context, String path) throws IOException {
        InputStream in = null;
        if (context != null && !StringUtil.isBlank(path)) {
            // In android os, opening asserts path could not start with /
            if (path.charAt(0) == '/') {
                path = path.substring(1);
            }
            AssetManager assets = context.getAssets();
            try {
                in = assets.open(path);
            } finally {
                IOUtil.close(in);
            }
        }
        return in;
    }

    /**
     * Open File for output in device's storage(device Memory).
     *
     * @param context Context, Activity OK
     * @param path    file path, relative
     * @return OutputStream or null
     */
    public static OutputStream openInternalFileOutput(Context context, String path) {
        OutputStream out = null;
        if (context != null && path != null && path.length() > 0) {
            char ch = path.charAt(0);
            // In android os, open asserts path must not / startsWith
            if (ch == '/') {
                path = path.substring(1);
            }
            try {
                out = context.openFileOutput(path, Context.MODE_PRIVATE);
            } catch (FileNotFoundException fnfe) {
                log.error("", fnfe);
            }
        }
        return out;
    }

    /**
     * Open file for read from device storage(device memory)
     *
     * @param context Context, Activity OK
     * @param path    file path, relative
     * @return InputStream or null(Not found)
     */
    public static InputStream openInternalFileInput(Context context, String path) {
        InputStream in = null;
        if (context != null && path != null && path.length() > 0) {
            char ch = path.charAt(0);
            // In android os, open asserts path must not / startsWith
            if (ch == '/') {
                path = path.substring(1);
            }
            try {
                in = context.openFileInput(path);
            } catch (FileNotFoundException e) {
                log.error("", e);
            }
        }
        return in;
    }

    /**
     * Get ExternalStorageDirectory Path
     *
     * @return path or null
     */
    public static String getExternalPath() {
        String strFilePath = null;
        File dir = Environment.getExternalStorageDirectory();
        String externalStorageState = Environment.getExternalStorageState();
        log.debug("ExternalStorageState is: " + externalStorageState);
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            if (dir != null && dir.exists()) {
                strFilePath = dir.getAbsolutePath();
            }
        }
        return strFilePath;
    }

    public static boolean saveDataToLocalRoot(Context ct, String path, String data) throws IOException {
        boolean bret = false;
        if (ct != null && path != null && data != null) {
            String r = getLocalRoot(ct);
            if (r != null) {
                File f = new File(r, path);
                OutputStream out = openFileOutputStream(f.getAbsolutePath());
                if (out != null) {
                    OutputStreamWriter w = null;
                    BufferedWriter bw = null;
                    try {
                        w = new OutputStreamWriter(out, "UTF-8");
                        bw = new BufferedWriter(w);
                        bw.write(data);
                    } finally {
                        IOUtil.close(bw);
                        IOUtil.close(w);
                        IOUtil.close(out);
                    }
                }
            }
        }
        return bret;
    }

    /**
     * Save data into local file
     *
     * @param ct   Context for getLocalRoot
     * @param path filepath
     * @param data byte[] data
     * @return boolean save ok?
     */
    public static boolean saveDataToLocalRoot(Context ct, String path, byte[] data) throws IOException {
        boolean bret = false;
        if (ct != null && path != null && data != null) {
            String r = getLocalRoot(ct);
            if (r != null) {
                File f = new File(r, path);
                File p = f.getParentFile();
                if (!p.exists()) {
                    p.mkdirs();
                }
                OutputStream out = openFileOutputStream(f.getAbsolutePath());
                if (out != null) {
                    try {
                        out.write(data, 0, data.length);
                    } finally {
                        IOUtil.close(out);
                    }
                }
            }
        }
        return bret;
    }

    /**
     * Load from application custom local file
     *
     * @param ct   Context for getLocalRoot
     * @param path file path and name, not a absolue path,
     * @return byte[] data
     */
    public static InputStream getStreamFromLocalRoot(Context ct, String path) throws FileNotFoundException {
    	InputStream in = null;
        if (ct != null && path != null) {
            String localRoot = getLocalRoot(ct);
            if (!StringUtil.isBlank(localRoot)) {
                File f = new File(localRoot, path);
                log.debug("getStreamFromLocalRoot(): " + f.getAbsolutePath());
                if (f.exists() && f.canRead()) {
                    in = openFileInputStream(f.getAbsolutePath());
                }
            }
        }
        return in;
    }

    /**
     * Get Local cache root in externalPath or internal
     *
     * @param ct Context
     * @return String
     */
    public static String getLocalRoot(Context ct) {
        String ret = null;
        if (ct != null) {
            String extPath = getExternalPath();
            // We always save data in Phone storage.
            StringBuilder sb = new StringBuilder();
            if (extPath != null) {
                sb.append(extPath).append("/Android/data/");
                sb.append(ct.getPackageName()).append("/cs");
//                ct.getExternalCacheDir();
            } else {
                File dir = ct.getCacheDir();
                String parent = dir.getParent();
                sb.append(parent).append("/cs");
            }
            ret = sb.toString();
        }
        return ret;
    }

    /**
     * Open File with path as Output Stream for write
     *
     * @param filename file path
     * @return OutputStream or null
     */
    public static OutputStream openFileOutputStream(String filename) {
        File cookieFile = new File(filename);
        OutputStream bos = null;
        try {
            bos = new FileOutputStream(cookieFile);
        } catch (FileNotFoundException e) {
            log.error("", e);
        }
        return bos;
    }

    /**
     * Open File with path as Input Stream for read
     *
     * @param filename file path
     * @return InputStream or null
     */
    public static InputStream openFileInputStream(String filename) throws FileNotFoundException {
        File fobj = new File(filename);
        return new FileInputStream(fobj);
    }

    public static byte[] gzipCompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gzipCompress(bais, baos);
        byte[] output = baos.toByteArray();
        baos.flush();
        baos.close();
        bais.close();
        return output;
    }

    private static void gzipCompress(InputStream is, OutputStream os) throws Exception {
        GZIPOutputStream gos = new GZIPOutputStream(os);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = is.read(data, 0, BUFFER)) != -1) {
            gos.write(data, 0, count);
        }
        gos.finish();
        gos.flush();
        gos.close();
    }

    private static void gzipDecompress(InputStream is, OutputStream os) throws Exception {
        GZIPInputStream gis = new GZIPInputStream(is);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }
        gis.close();
    }

    public static byte[] gzipDecompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gzipDecompress(bais, baos);
        data = baos.toByteArray();
        baos.flush();
        baos.close();
        bais.close();
        return data;
    }

    /**
     * 将Bitmap转换为byte[]
     * @param bm Bitmap
     * @return byte[] PNG数据
     */
    public static byte[] bitmapBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * Delete file with path
     *
     * @param strFileName file path
     * @return delete OK?
     */
    public boolean deleteFile(String strFileName) {
        boolean bret = false;
        try {
            File cFile = new File(strFileName);
            if (cFile.exists()) {
                bret = cFile.delete();
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return bret;
    }

    /**
     * Find key content in data buffer.
     *
     * @param data     byte[]
     * @param key      find keyword
     * @param startPos start Position in data
     * @return index found.
     */
    public static int findContent(byte[] data, String key, int startPos) {
        int ret = -1;
        if (data != null && key != null) {
            int dl = data.length;
            if (startPos < 0) {
                startPos = 0;
            }
            if (dl > 0 && startPos < dl) {
                byte[] buf = key.getBytes();
                int bl = buf.length;
                if (startPos + bl < dl) {
                    for (int i = startPos; i < dl; i++) {
                        int mask = 1;
                        for (int j = 0; j < bl; j++) {
                            byte b1 = data[i + j];
                            byte b2 = buf[j];
                            if (b1 != b2) {
                                mask &= 0;
                                break;
                            } else {
                                mask &= 1;
                            }
                        }
                        if (mask == 1) {
                            // TODO Found, return index to caller.
                            ret = i;
                            break;
                        }
                    }
                }
            }
        }

        return ret;
    }

}
