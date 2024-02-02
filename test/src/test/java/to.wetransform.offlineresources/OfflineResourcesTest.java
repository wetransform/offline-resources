package to.wetransform.offlineresources;

import org.junit.jupiter.api.Test;
import to.wetransform.offlineresources.api.OfflineResources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class OfflineResourcesTest {

  @Test
  public void testLoadXmlSchema() throws IOException {
    testLoadLocation(URI.create(
      "https://www.w3.org/2001/XMLSchema.xsd"
    ));
  }

  @Test
  public void testLoadInspireHyP4() throws IOException {
    testLoadLocation(URI.create(
      "https://inspire.ec.europa.eu/schemas/hy-p/4.0/HydroPhysicalWaters.xsd"
    ));
  }

  /**
   * Test workaround for missing schemas in INSPIRE repository that are compensated with redirects on the INSPIRE website.
   *
   * See https://github.com/halestudio/hale-platform/blob/1e7e00c9684c9043c98a857338fd1ccfee05541b/modules/resources/build.gradle#L157
   */
  @Test
  public void testLoadInspireRedirectWorkaround() throws IOException {
    testLoadLocation(URI.create(
      "https://inspire.ec.europa.eu/schemas/lc/0.0/LandCover.xsd"
    ));

    testLoadLocation(URI.create(
      "https://inspire.ec.europa.eu/schemas/wfd/0.0/WaterFrameworkDirective.xsd"
    ));
  }

  @Test
  public void testLoadNonExistentFail() throws IOException {
    assertThrows(Exception.class, () -> {
      testLoadLocation(URI.create(
        "https://example.com/example.xsd"
      ));
    });
  }

  // helpers

  private void testLoadLocation(URI location) throws IOException {
    OfflineResources or = new OfflineResources();

    Supplier<InputStream> input = or.resolve(location);

    try (InputStream in = input.get(); BufferedReader reader = new BufferedReader(
      new InputStreamReader(in, StandardCharsets.UTF_8))) {
      // consume content
      reader.lines();
    }
  }

}
