using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Server.Kestrel.Core;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Tweetinvi;


namespace TwitterClient
{
    public class Startup
    {
        public Startup()
        {

        }

        public static readonly string MyAllowSpecificOrigins = "_myAllowSpecificOrigins";

        public void ConfigureServices(IServiceCollection services)
        {
            Auth.SetUserCredentials("6fLuXUh0enly5O6imWzeLgKfI", "ogZbigHWoGaswkkbTEYAGUf6wFRNYUZppFVwyOSTGPZvKykbdK",
                "2513271864-nbWly1iXCZwiBs65VTz4WyY4IaV6Yz2FKDivEOv", "tpfbKnt099xYxs6Qv0eSzunNNuvR8KQw5Sumjt8aCSjLm");

            services
                .AddMvcCore(x => x.EnableEndpointRouting = false)
                .AddApiExplorer()
                .SetCompatibilityVersion(CompatibilityVersion.Version_3_0)
                .AddCors(
                    options =>
                    {
                        options.AddPolicy(MyAllowSpecificOrigins,
                            builder =>
                            {
                                builder
                                    .AllowAnyOrigin()
                                    .AllowAnyHeader()
                                    .AllowAnyMethod()
                                    ;
                            });
                    })
                ;

           // services.Swagger();

        }

        public async void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            //app.Swagger();

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
                app.UseHsts();
            }

            app.UseStaticFiles();
            app.UseCors(MyAllowSpecificOrigins);
            app.UseMvcWithDefaultRoute();
            app.UseWelcomePage();
        }
    }
}
