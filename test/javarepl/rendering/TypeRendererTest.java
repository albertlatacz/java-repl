package javarepl.rendering;

import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static javarepl.rendering.TypeRenderer.renderType;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TypeRendererTest {
    @Test
    public void supportsGenericTypesRendering() {
        assertThat(renderType(new ArrayList<String>() {
        }.getClass()), is("java.util.ArrayList<java.lang.String>"));
        assertThat(renderType(asList(1, 2, 3).getClass()), is("java.util.AbstractList<Object>"));
    }
}
